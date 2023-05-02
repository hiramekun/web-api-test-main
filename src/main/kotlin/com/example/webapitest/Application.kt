package com.example.webapitest

import com.example.webapitest.repository.UserRepository
import com.example.webapitest.service.UserService
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    install(ContentNegotiation) {
        jackson()
    }

    connectDB()

    val userService = UserService(UserRepository)

    routing {
        userRoutes(userService)
    }
}

private fun connectDB() {
    val hikariConfig = HikariConfig()
    hikariConfig.driverClassName = "com.mysql.cj.jdbc.Driver"
    hikariConfig.jdbcUrl = "jdbc:mysql://localhost:3316/shiharai?allowPublicKeyRetrieval=true&useSSL=false"
    hikariConfig.username = "testuser"
    hikariConfig.password = "password"
    hikariConfig.maximumPoolSize = 3
    hikariConfig.isAutoCommit = false
    hikariConfig.transactionIsolation = "TRANSACTION_REPEATABLE_READ"

    val dataSource = HikariDataSource(hikariConfig)
    Database.connect(dataSource)
}