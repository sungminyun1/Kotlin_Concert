package kr.hhplus.be.server.Interfaces.api.concert.dto

data class ConcertReserveRequest(
    val userId: String,
    val concertDetailId: String,
    val seat: Int
)
