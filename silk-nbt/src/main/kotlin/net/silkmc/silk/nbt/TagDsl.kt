package net.silkmc.silk.nbt

import net.minecraft.nbt.*

fun ByteTag(data: Number) = ByteTag.valueOf(data.toByte())!!
fun ByteTag(data: Boolean) = ByteTag.valueOf(data)!!
fun ShortTag(data: Number) = ShortTag.valueOf(data.toShort())!!
fun IntTag(data: Number) = IntTag.valueOf(data.toInt())!!
fun LongTag(data: Number) = LongTag.valueOf(data.toLong())!!
fun FloatTag(data: Number) = FloatTag.valueOf(data.toFloat())!!
fun DoubleTag(data: Number) = DoubleTag.valueOf(data.toDouble())!!
fun StringTag(data: String) = StringTag.valueOf(data)!!

fun <T : Tag> ListTag(vararg elements: T) = net.minecraft.nbt.ListTag().also { it += elements }
fun <T : Tag> ListTag(elements: Iterable<T>) = net.minecraft.nbt.ListTag().also { it += elements }

inline fun compoundTag(build: CompoundTagBuilderScope.() -> Unit) = CompoundTagBuilderScope().also(build).tag
inline fun <T : Tag> listTag(build: ListTagBuilderScope<T>.() -> Unit) = ListTagBuilderScope<T>().also(build).tag

@DslMarker
private annotation class TagDslMarker

@TagDslMarker
class CompoundTagBuilderScope {
    val tag = CompoundTag()

    inline operator fun String.invoke(provider: () -> Tag) {
        tag[this] = provider()
    }

    operator fun String.invoke(data: Boolean) {
        tag[this] = data
    }

    operator fun String.invoke(data: Byte) {
        tag[this] = data
    }

    operator fun String.invoke(data: Short) {
        tag[this] = data
    }

    operator fun String.invoke(data: Int) {
        tag[this] = data
    }

    operator fun String.invoke(data: Long) {
        tag[this] = data
    }

    operator fun String.invoke(data: Float) {
        tag[this] = data
    }

    operator fun String.invoke(data: Double) {
        tag[this] = data
    }

    operator fun String.invoke(data: String) {
        tag[this] = data
    }

    operator fun String.invoke(data: ByteArray) {
        tag[this] = data
    }

    @JvmName("putByteList")
    operator fun String.invoke(data: List<Byte>) {
        tag[this] = data
    }

    @JvmName("putByteIterable")
    operator fun String.invoke(data: Iterable<Byte>) = this(data.toList())

    operator fun String.invoke(data: IntArray) {
        tag[this] = data
    }

    @JvmName("putIntList")
    operator fun String.invoke(data: List<Int>) {
        tag[this] = data
    }

    @JvmName("putIntIterable")
    operator fun String.invoke(data: Iterable<Int>) = this(data.toList())

    operator fun String.invoke(data: LongArray) {
        tag[this] = data
    }

    @JvmName("putLongList")
    operator fun String.invoke(data: List<Long>) {
        tag[this] = data
    }

    @JvmName("putLongIterable")
    operator fun String.invoke(data: Iterable<Long>) = this(data.toList())

    @JvmName("putTagList")
    operator fun String.invoke(data: List<Tag>) {
        tag[this] = data.toTag()
    }

    @JvmName("putBooleanList")
    operator fun String.invoke(data: List<Boolean>) = this(data.toTag())

    @JvmName("putShortList")
    operator fun String.invoke(data: List<Short>) = this(data.toTag())

    @JvmName("putFloatList")
    operator fun String.invoke(data: List<Float>) = this(data.toTag())

    @JvmName("putDoubleList")
    operator fun String.invoke(data: List<Double>) = this(data.toTag())

    @JvmName("putStringList")
    operator fun String.invoke(data: List<String>) = this(data.toTag())
}

@TagDslMarker
class ListTagBuilderScope<T : Tag> {
    val tag = ListTag<T>()

    operator fun String.unaryPlus() = tag.add(toTag())
    operator fun T.unaryPlus() = tag.add(this)
}