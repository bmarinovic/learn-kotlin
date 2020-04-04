package com.example.domain.users

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object UsersTable : LongIdTable() {
    val email: Column<String> = varchar("email", 50)
    val firstName: Column<String> = varchar("firstName", 50)
    val lastName: Column<String> = varchar("lastName", 50)
    val password: Column<String> = varchar("password", 50)
    val role: Column<String> = varchar("role", 50)
    val createdAt: Column<DateTime> = datetime("createdAt")
    val updatedAt: Column<DateTime> = datetime("updatedAt")
    override val primaryKey = PrimaryKey(id, name = "PK_UsersTable_Id")
}