package com.example.webapitest

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    connectDB()
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