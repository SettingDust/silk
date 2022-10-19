package net.silkmc.silk.nbt

import net.minecraft.nbt.*
import java.util.*

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Boolean.toNbt(): ByteTag = ByteTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Byte.toNbt(): ByteTag = ByteTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Short.toNbt(): ShortTag = ShortTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Int.toNbt(): IntTag = IntTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Long.toNbt(): LongTag = LongTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Float.toNbt(): FloatTag = FloatTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Double.toNbt(): DoubleTag = DoubleTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun Char.toNbt(): IntTag = IntTag(code)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun String.toNbt(): StringTag = StringTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun ByteArray.toNbt() = ByteArrayTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun List<Byte>.toNbt() = ByteArrayTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun IntArray.toNbt() = IntArrayTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun List<Int>.toNbt() = IntArrayTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun LongArray.toNbt() = LongArrayTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun List<Long>.toNbt() = LongArrayTag(this)

@Deprecated("Renamed to `toTag()`", ReplaceWith("toTag()"))
fun UUID.toNbt(): IntArrayTag = NbtUtils.createUUID(this)