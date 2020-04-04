package com.example.domain.database

import com.example.domain.users.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DbSettings {
    val db by lazy {
        val db = Database.connect(
            "jdbc:postgresql://localhost:55432/learn_kotlin", driver = "org.postgresql.Driver",
            user = "postgres", password = "postgres"
        )

        transaction {
            SchemaUtils.create(UsersTable)
        }

        db
    }
}