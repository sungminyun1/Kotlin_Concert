package kr.hhplus.be.server.Interfaces.api.concert.dto

import java.time.LocalDateTime

data class ConcertResponse(
    val id: String,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
) {
}