package com.example.domain.database

import org.jetbrains.exposed.sql.Database

object DbSettings {
    val db by lazy {
        Database.connect("jdbc:h2:mem:regular", driver = "org.h2.Driver")
    }
}