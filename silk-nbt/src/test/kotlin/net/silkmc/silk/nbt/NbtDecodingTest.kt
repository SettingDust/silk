package net.silkmc.silk.nbt

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.Exhaustive
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.enum
import io.kotest.property.forAll
import net.minecraft.nbt.ListTag
import net.silkmc.silk.nbt.serialization.Nbt
import net.silkmc.silk.nbt.serialization.UnknownKeyException
import net.silkmc.silk.nbt.serialization.decodeFromNbtElement

class NbtDecodingTest : StringSpec({
    "compound should decode to class" {
        val value = TestClass(
            10,
            -1248,
            "Joe",
            listOf("abc", "äèðßèäßð", "1 2"),
            setOf(5, 34, 9, 3443),
            InnerTestClass(true),
            null
        )
        val compound = compoundTag {
            "x"(value.x)
            "y"(value.y)
            "name"(value.name)
            "stringList"(value.stringList)
            "longSet"(value.longSet)
            "inner" {
                compoundTag { "test"(value.inner.test) }
            }
            "nullable"(listOf<Short>())
        }
        Nbt.decodeFromNbtElement<TestClass>(compound) shouldBe value
    }

    "lists should decode to nullable types" {
        Nbt.decodeFromNbtElement<Int?>(ListTag()) shouldBe null
        Nbt.decodeFromNbtElement<Int?>(ListTag().apply { add(5.toTag()) }) shouldBe 5
    }

    "byte array should decode to collections" {
        checkAll(Arb.byteArray(Arb.int(0..0x1000), Arb.byte())) {
            val nbt = it.toTag()
            val list = it.toList()
            Nbt.decodeFromNbtElement<ByteArray>(nbt) shouldBe it
            Nbt.decodeFromNbtElement<List<Byte>>(nbt) shouldBe list
        }
        checkAll(Arb.set(Arb.byte(), 0..Byte.MAX_VALUE)) {
            val nbt = it.toList().toTag()
            Nbt.decodeFromNbtElement<Set<Byte>>(nbt) shouldBe it
        }
    }

    "int array should decode to collections" {
        checkAll(Arb.list(Arb.int(), 0..0x1000)) {
            val nbt = it.toTag()
            val array = it.toIntArray()
            Nbt.decodeFromNbtElement<IntArray>(nbt) shouldBe array
            Nbt.decodeFromNbtElement<List<Int>>(nbt) shouldBe it
        }
        checkAll(Arb.set(Arb.int(), 0..0x1000)) {
            val nbt = it.toList().toTag()
            Nbt.decodeFromNbtElement<Set<Int>>(nbt) shouldBe it
        }
    }

    "long array should decode to collections" {
        checkAll(Arb.list(Arb.long(), 0..0x1000)) {
            val nbt = it.toTag()
            val array = it.toLongArray()
            Nbt.decodeFromNbtElement<LongArray>(nbt) shouldBe array
            Nbt.decodeFromNbtElement<List<Long>>(nbt) shouldBe it
        }
        checkAll(Arb.set(Arb.long(), 0..0x1000)) {
            val nbt = it.toList().toTag()
            Nbt.decodeFromNbtElement<Set<Long>>(nbt) shouldBe it
        }
    }

    "strings should decode to enums" {
        forAll(Exhaustive.enum<TestEnum>()) {
            Nbt.decodeFromNbtElement<TestEnum>(it.name.toTag()) == it
        }
    }

    "closed polymorphism should decode correctly" {
        val value = SealedChild1(1f, 2.5)
        val compound = compoundTag {
            "type"("child1")
            "value" {
                compoundTag {
                    "baseVal"(value.baseVal)
                    "childProp"(value.childProp)
                }
            }
        }
        Nbt.decodeFromNbtElement<SealedBase>(compound) shouldBe value
    }

    "decoding should honor ignoreUnknownKeys setting" {
        val withUnknown = compoundTag {
            "one"(1)
            "unknown"(4.5)
        }
        shouldThrow<UnknownKeyException> {
            Nbt.decodeFromNbtElement<TestClassWithDefault>(withUnknown)
        }.key shouldBe "unknown"
        Nbt {
            ignoreUnknownKeys = true
        }.decodeFromNbtElement<TestClassWithDefault>(withUnknown) shouldBe TestClassWithDefault(1)
    }
})
