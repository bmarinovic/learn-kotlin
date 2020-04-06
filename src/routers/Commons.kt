package com.example.routers

import io.ktor.locations.Location

@Location("{id}")
data class IntId(val id: Long)
