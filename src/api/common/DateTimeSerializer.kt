package com.example.api.common

import kotlinx.serialization.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter


@Serializer(forClass = DateTime::class)
object DateTimeSerializer : KSerializer<DateTime> {
    private val dtf: DateTimeFormatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss")

    override val descriptor: SerialDescriptor =
        PrimitiveDescriptor("WithCustomDefault", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeString(dtf.print(value))
    }

    override fun deserialize(decoder: Decoder): DateTime {
        return dtf.parseDateTime(decoder.decodeString())
    }
}