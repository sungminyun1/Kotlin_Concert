package kr.hhplus.be.server.Interfaces.api.member.dto

import java.time.LocalDateTime

data class PointResponse(
    val userId: String,
    val amount: Long,
    val updatedAt: LocalDateTime,
) {
}