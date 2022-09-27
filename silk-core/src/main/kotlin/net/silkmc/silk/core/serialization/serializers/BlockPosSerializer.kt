package net.silkmc.silk.core.serialization.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.minecraft.core.BlockPos
import net.silkmc.silk.core.serialization.SilkSerializer

@ExperimentalSerializationApi
object BlockPosSerializer : SilkSerializer<BlockPos>() {
    private const val XIndex = 0;
    private const val YIndex = 1;
    private const val ZIndex = 2;

    override val descriptor = buildClassSerialDescriptor(descriptorName) {
        element<Int>("x")
        element<Int>("y")
        element<Int>("z")
    }

    override fun deserialize(decoder: Decoder) = run {
        val composite = decoder.beginStructure(descriptor)
        if (composite.decodeSequentially()) {
            BlockPos(
                composite.decodeIntElement(descriptor, XIndex),
                composite.decodeIntElement(descriptor, YIndex),
                composite.decodeIntElement(descriptor, ZIndex)
            )
        } else {
            var x: Int? = null
            var y: Int? = null
            var z: Int? = null
            while (true) {
                when (val i = composite.decodeElementIndex(descriptor)) {
                    CompositeDecoder.DECODE_DONE -> break
                    XIndex -> x = composite.decodeIntElement(descriptor, i)
                    YIndex -> y = composite.decodeIntElement(descriptor, i)
                    ZIndex -> z = composite.decodeIntElement(descriptor, i)
                }
            }
            BlockPos(x!!, y!!, z!!)
        }
    }

    override fun serialize(encoder: Encoder, value: BlockPos) = encoder.encodeLong(value.asLong())
}

@ExperimentalSerializationApi
object BlockPosAsLongSerializer : SilkSerializer<BlockPos>() {
    override val descriptor = PrimitiveSerialDescriptor(descriptorName, PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder) = BlockPos.of(decoder.decodeLong())!!

    override fun serialize(encoder: Encoder, value: BlockPos) = encoder.encodeLong(value.asLong())
}