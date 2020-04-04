package com.example.domain.users

import com.example.core.Role
import org.joda.time.DateTime

data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val role: Role,
    val createdAt: DateTime,
    val updatedAt: DateTime
)