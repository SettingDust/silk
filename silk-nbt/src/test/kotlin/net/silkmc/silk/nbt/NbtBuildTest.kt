package net.silkmc.silk.nbt

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.StringTag

class NbtBuildTest : StringSpec({
    "should build compound tag correctly" {
        val case = compoundTag {
            "id"("minecraft:grass")
            "Count" { ByteTag(5) }
            "tag" {
                compoundTag {
                    "type"("stone")
                }
            }
        }
        case["id"] shouldBe StringTag("minecraft:grass")
        case["Count"] shouldBe ByteTag(5)
        val tag = case["tag"].shouldBeInstanceOf<CompoundTag>()
        tag["type"] shouldBe StringTag("stone")
    }
    "should build list tag correctly" {
        listTag<StringTag> {
            +"0"
            +"1"
        } shouldBe listOf(StringTag("0"), StringTag("1"))

        ListTag(IntTag(0)) shouldBe listOf(IntTag(0))
    }
})