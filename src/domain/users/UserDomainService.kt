package com.example.domain.users

import com.example.core.toRole
import com.example.domain.database.DbSettings.db
import com.google.inject.ImplementedBy
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

@ImplementedBy(UserDomainServiceImpl::class)
interface UserDomainService {
    suspend fun findById(id: Long): User?
    suspend fun readAll(): List<User>
    suspend fun insert(userWrite: UserWrite): User
    suspend fun update(id: Long, userWrite: UserWrite): User?
    suspend fun delete(id: Long): Boolean
}

class UserDomainServiceImpl : UserDomainService {
    override suspend fun findById(id: Long): User? =
        transaction(db) {
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
        transaction(db) {
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

        val id = transaction(db) {
            UsersTable.insertAndGetId {
                it[email] = userWrite.email
                it[firstName] = userWrite.firstName
                it[lastName] = userWrite.lastName
                it[password] = userWrite.password
                it[role] = userWrite.role.name
                it[createdAt] = DateTime.now()
                it[updatedAt] = DateTime.now()
            }
        }

        return findById(id.value)!!
    }

    override suspend fun update(id: Long, userWrite: UserWrite): User? {
        transaction(db) {
            UsersTable.update({ UsersTable.id eq id }) {
                it[email] = userWrite.email
                it[firstName] = userWrite.firstName
                it[lastName] = userWrite.lastName
                it[password] = userWrite.password
                it[role] = userWrite.role.name
                it[updatedAt] = DateTime.now()
            }
        }

        return findById(id)
    }

    override suspend fun delete(id: Long): Boolean =
        transaction(db) {
            UsersTable.deleteWhere { UsersTable.id eq id }
        } > 0
}


