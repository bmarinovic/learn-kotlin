package com.example.api.users

import com.example.domain.users.UserDomainService
import com.google.inject.ImplementedBy

@ImplementedBy(UserApiServiceImpl::class)
interface UserApiService {
    suspend fun findById(id: Long): User?
    suspend fun readAll(): List<User>
    suspend fun insert(userWrite: UserWrite): User
    suspend fun update(id: Long, userWrite: UserWrite): User?
    suspend fun delete(id: Long): Boolean
}

class UserApiServiceImpl(private val userDomainService: UserDomainService) : UserApiService {
    override suspend fun findById(id: Long): User? =
        userDomainService.findById(id)?.toApi()

    override suspend fun readAll(): List<User> =
        userDomainService.readAll().map { it.toApi() }

    override suspend fun insert(userWrite: UserWrite): User =
        userDomainService.insert(userWrite.toDomain()).toApi()

    override suspend fun update(id: Long, userWrite: UserWrite): User? =
        userDomainService.update(id, userWrite.toDomain())?.toApi()

    override suspend fun delete(id: Long): Boolean =
        userDomainService.delete(id)
}