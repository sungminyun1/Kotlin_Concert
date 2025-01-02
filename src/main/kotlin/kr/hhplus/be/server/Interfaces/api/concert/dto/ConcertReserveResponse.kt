package kr.hhplus.be.server.Interfaces.api.concert.dto

import java.time.LocalDateTime

data class ConcertReserveResponse(
    val concertDetailId: String,
    val concertName: String,
    val userId: String,
    val reserveId: String,
    val reserveTime: LocalDateTime,
) {
}