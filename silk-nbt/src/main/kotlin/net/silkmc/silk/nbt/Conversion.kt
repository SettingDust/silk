package net.silkmc.silk.nbt

import net.minecraft.nbt.*
import net.silkmc.silk.nbt.dsl.*
import java.util.*

fun Boolean.toTag(): ByteTag = ByteTag(this)
fun Byte.toTag(): ByteTag = ByteTag(this)
fun Short.toTag(): ShortTag = ShortTag(this)
fun Int.toTag(): IntTag = IntTag(this)
fun Long.toTag(): LongTag = LongTag(this)
fun Float.toTag(): FloatTag = FloatTag(this)
fun Double.toTag(): DoubleTag = DoubleTag(this)
fun Char.toTag(): IntTag = IntTag(code)
fun String.toTag(): StringTag = StringTag(this)

fun ByteArray.toTag() = ByteArrayTag(this)
fun List<Byte>.toTag() = ByteArrayTag(this)
fun IntArray.toTag() = IntArrayTag(this)
fun List<Int>.toTag() = IntArrayTag(this)
fun LongArray.toTag() = LongArrayTag(this)
fun List<Long>.toTag() = LongArrayTag(this)

fun ByteArray.toListTag() = map(Byte::toTag).toTag()

@JvmName("toByteListTag")
fun List<Byte>.toListTag() = map(Byte::toTag).toTag()
fun IntArray.toListTag() = map(Int::toTag).toTag()

@JvmName("toIntListTag")
fun List<Int>.toListTag() = map(Int::toTag).toTag()
fun LongArray.toListTag() = map(Long::toTag).toTag()

@JvmName("toLongListTag")
fun List<Long>.toListTag() = map(Long::toTag).toTag()

fun <T : Tag> List<T>.toTag() = ListTag(this)

@JvmName("booleanListToTag")
fun List<Boolean>.toTag() = map(Boolean::toTag).toTag()

@JvmName("shortListToTag")
fun List<Short>.toTag() = map(Short::toTag).toTag()

@JvmName("floatListToTag")
fun List<Float>.toTag() = map(Float::toTag).toTag()

@JvmName("doubleListToTag")
fun List<Double>.toTag() = map(Double::toTag).toTag()

@JvmName("stringListToTag")
fun List<String>.toTag() = map(String::toTag).toTag()