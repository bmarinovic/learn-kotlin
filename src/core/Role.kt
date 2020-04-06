package com.example.core

import kotlinx.serialization.*

@Serializable
sealed class Role(val level: Int, val name: String) {

    @Serializable
    object ADMIN_ROLE : Role(100, "Admin")

    @Serializable
    object USER_ROLE : Role(10, "User")

    @Serializable
    object NO_ROLE : Role(0, "None")
}

fun String.toRole(): Role = when (this) {
    Role.ADMIN_ROLE.name -> Role.ADMIN_ROLE
    Role.USER_ROLE.name -> Role.USER_ROLE
    else -> Role.NO_ROLE
}

@Serializer(forClass = Role::class)
object RoleSerializer : KSerializer<Role> {
    override val descriptor: SerialDescriptor = PrimitiveDescriptor("RoleStringSerializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Role =
        decoder.decodeString().toRole()

    override fun serialize(encoder: Encoder, value: Role) =
        encoder.encodeString(value.name)
}
