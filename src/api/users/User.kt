package com.example.api.users

import com.example.api.common.DateTimeSerializer
import com.example.core.Role
import com.example.core.RoleSerializer
import kotlinx.serialization.Serializable
import org.joda.time.DateTime

@Serializable
data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    @Serializable(with = RoleSerializer::class) val role: Role,
    @Serializable(with = DateTimeSerializer::class) val createdAt: DateTime,
    @Serializable(with = DateTimeSerializer::class) val updatedAt: DateTime
)