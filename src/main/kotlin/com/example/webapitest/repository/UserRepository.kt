package com.example.webapitest.repository

import com.example.webapitest.model.User
import com.example.webapitest.repository.db.UserEntity
import com.example.webapitest.repository.db.toUser

object UserRepository {

    fun getByOrNull(id: Long): User? {
        return UserEntity.findById(id)?.toUser()
    }

    fun list(): List<User> {
        return UserEntity.all().map { it.toUser() }
    }

    fun insert(user: User): User {
        val entity = UserEntity.new {
            organizationId = user.organizationId
            name = user.name
            email = user.email
            password = user.password
            verified = user.verified
        }

        return entity.toUser()
    }
}