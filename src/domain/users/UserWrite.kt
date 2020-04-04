package com.example.domain.users

import com.example.core.Role

data class UserWrite(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val role: Role
)