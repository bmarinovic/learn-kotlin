package com.example.api.users

import kotlinx.serialization.Serializable


@Serializable
data class User(val id: Int, val name: String, val password: String? = null)