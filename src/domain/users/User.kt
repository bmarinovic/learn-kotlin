package com.example.domain.users

import com.example.core.Role
import java.time.LocalDateTime

data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val role: Role,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)