package kr.hhplus.be.server.Interfaces.api.payment.dto

import java.time.LocalDateTime

data class PaymentResponse(
    val reservationId: String,
    val userId: String,
    val date: LocalDateTime,
)
