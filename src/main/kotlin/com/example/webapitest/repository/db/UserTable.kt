package com.example.webapitest.repository.db

import com.example.webapitest.model.User
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

object UserTable : LongIdTableBase("users") {
    val organizationId = long("organization")
    val name = varchar("name", 255)
    val email = varchar("email", 255)
    val password = varchar("password", 255)
    val verified = bool("verified")
}

class UserEntity(id: EntityID<Long>) : LongEntityBase(id) {
    companion object : LongEntityClass<UserEntity>(UserTable)

    var organizationId by UserTable.organizationId
    var name by UserTable.name
    var email by UserTable.email
    var password by UserTable.password
    var verified by UserTable.verified
    override var createdAt by UserTable.createdAt
    override var updatedAt by UserTable.updatedAt
}

fun UserEntity.toUser() = User(
    id = this.id.value,
    organizationId = this.organizationId,
    name = this.name,
    email = this.email,
    password = this.password,
    verified = this.verified,
)