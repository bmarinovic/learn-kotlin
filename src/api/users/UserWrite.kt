package com.example.api.users

import com.example.core.Role
import com.example.core.RoleSerializer
import kotlinx.serialization.Serializable

@Serializable
data class UserWrite(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    @Serializable(with = RoleSerializer::class) val role: Role
)