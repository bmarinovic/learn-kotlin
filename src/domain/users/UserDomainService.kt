package com.example.domain.users

import com.example.core.toRole
import com.example.domain.database.DbSettings
import com.google.inject.ImplementedBy
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

@ImplementedBy(UserDomainServiceImpl::class)
interface UserDomainService {
    suspend fun findById(id: Long): User?
    suspend fun readAll(): List<User>
    suspend fun insert(userWrite: UserWrite): User
    suspend fun update(id: Long, userWrite: UserWrite): User?
    suspend fun delete(id: Long): Boolean
}

class UserDomainServiceImpl(private val dbSettings: DbSettings) : UserDomainService {
    override suspend fun findById(id: Long): User? =
        transaction(dbSettings.db) {
            UsersTable.select { UsersTable.id eq id }.singleOrNull()?.let {
                User(
                    it[UsersTable.id].value,
                    it[UsersTable.email],
                    it[UsersTable.firstName],
                    it[UsersTable.lastName],
                    it[UsersTable.password],
                    it[UsersTable.role].toRole(),
                    it[UsersTable.createdAt],
                    it[UsersTable.updatedAt]
                )
            }
        }

    override suspend fun readAll(): List<User> =
        transaction(dbSettings.db) {
            UsersTable.selectAll().map {
                User(
                    it[UsersTable.id].value,
                    it[UsersTable.email],
                    it[UsersTable.firstName],
                    it[UsersTable.lastName],
                    it[UsersTable.password],
                    it[UsersTable.role].toRole(),
                    it[UsersTable.createdAt],
                    it[UsersTable.updatedAt]
                )
            }
        }


    override suspend fun insert(userWrite: UserWrite): User {

        val id = transaction(dbSettings.db) {
            UsersTable.insertAndGetId {
                it[email] = userWrite.email
                it[firstName] = userWrite.firstName
                it[lastName] = userWrite.lastName
                it[password] = userWrite.password
                it[role] = userWrite.role.name
                it[createdAt] = LocalDateTime.now()
                it[updatedAt] = LocalDateTime.now()
            }
        }

        return findById(id.value)!!
    }

    override suspend fun update(id: Long, userWrite: UserWrite): User? {
        transaction(dbSettings.db) {
            UsersTable.update({ UsersTable.id eq id }) {
                it[email] = userWrite.email
                it[firstName] = userWrite.firstName
                it[lastName] = userWrite.lastName
                it[password] = userWrite.password
                it[role] = userWrite.role.name
                it[updatedAt] = LocalDateTime.now()
            }
        }

        return findById(id)
    }

    override suspend fun delete(id: Long): Boolean =
        transaction(dbSettings.db) {
            UsersTable.deleteWhere { UsersTable.id eq id }
        } > 0
}


