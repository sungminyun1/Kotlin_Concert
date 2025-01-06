package kr.hhplus.be.server.Interfaces.api.payment.dto

data class PaymentRequest(
    val reservationId: String,
    val userId: String,
)
