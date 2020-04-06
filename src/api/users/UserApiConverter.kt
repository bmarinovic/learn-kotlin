package com.example.api.users

import com.example.domain.users.User as DomainUser
import com.example.domain.users.UserWrite as DomainUserWrite


fun DomainUser.toApi(): User =
    User(this.id, this.email, this.firstName, this.lastName, this.role, this.createdAt, this.updatedAt)

fun UserWrite.toDomain(): DomainUserWrite =
    DomainUserWrite(this.email, this.firstName, this.lastName, this.password, this.role)