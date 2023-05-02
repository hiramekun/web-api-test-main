package com.example.webapitest.repository.db

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.javatime.date

object PaymentTable : LongIdTableBase("payments") {
    val userId = integer("user_id")
    val amount = integer("amount")
    val fee = integer("fee")
    val feeRate = decimal("fee_rate", 5, 5)
    val taxRate = integer("tax_rate")
    val billingAmount = integer("billing_amount")
    val transferDate = date("transfer_date")
    val uploadedDate = date("uploaded_date")
    val status = integer("status")
}

class PaymentEntity(id: EntityID<Long>) : LongEntityBase(id) {
    companion object : LongEntityClass<PaymentEntity>(PaymentTable)

    var userId by PaymentTable.userId
    var amount by PaymentTable.amount
    var fee by PaymentTable.fee
    var feeRate by PaymentTable.feeRate
    var taxRate by PaymentTable.taxRate
    var billingAmount by PaymentTable.billingAmount
    var transferDate by PaymentTable.transferDate
    var uploadedDate by PaymentTable.uploadedDate
    var status by PaymentTable.status
    override var createdAt by PaymentTable.createdAt
    override var updatedAt by PaymentTable.updatedAt
}
