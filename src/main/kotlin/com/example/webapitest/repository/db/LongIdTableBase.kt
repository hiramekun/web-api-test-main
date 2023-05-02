package com.example.webapitest.repository.db

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

abstract class LongIdTableBase(name: String) : LongIdTable(name) {
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}

abstract class LongEntityBase(id: EntityID<Long>) : LongEntity(id) {
    abstract var createdAt: LocalDateTime
    abstract var updatedAt: LocalDateTime
}

fun <T : Table> T.wildcard(vararg excludes: Expression<*>): Array<Expression<*>> =
    fields.filter { !excludes.contains(it) }.toTypedArray()
