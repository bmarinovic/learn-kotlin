package com.example.api.users

import com.example.api.common.LocalDateTimeSerializer
import com.example.core.Role
import com.example.core.RoleSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    @Serializable(with = RoleSerializer::class) val role: Role,
    @Serializable(with = LocalDateTimeSerializer::class) val createdAt: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) val updatedAt: LocalDateTime
)