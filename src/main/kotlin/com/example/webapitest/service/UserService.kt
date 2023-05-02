package com.example.webapitest.service

import com.example.webapitest.model.User
import com.example.webapitest.repository.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UserService(private val userRepository: UserRepository) {
    fun getByOrNull(id: Long): User? {
        return transaction { userRepository.getByOrNull(id) }
    }
}