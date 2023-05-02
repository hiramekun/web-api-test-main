package com.example.webapitest.model

data class User(
    val id: Long?,
    val organizationId: Long,
    val name: String,
    val email: String,
    val password: String,
    val verified: Boolean,
)
