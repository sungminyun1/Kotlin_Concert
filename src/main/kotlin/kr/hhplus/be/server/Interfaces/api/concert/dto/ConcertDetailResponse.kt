package kr.hhplus.be.server.Interfaces.api.concert.dto

import java.time.LocalDateTime

data class ConcertDetailResponse(
    val id: String,
    val name: String,
    val date: LocalDateTime,
    val seats: List<Seat>
) {
}

data class Seat(
    val id: Int,
    val avail: Boolean
)