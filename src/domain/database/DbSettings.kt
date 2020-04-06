package com.example.domain.database

import com.example.core.config.Config
import com.example.domain.users.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DbSettings(private val config: Config) {
    val db by lazy {
        val db = Database.connect(
            "jdbc:postgresql://${config.database.host}:${config.database.port}/${config.database.databaseName}",
            user = config.database.user, password = config.database.password
        )

        transaction {
            SchemaUtils.create(UsersTable)
        }

        db
    }
}