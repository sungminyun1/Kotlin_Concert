package kr.hhplus.be.server.Interfaces.api.concert.dto

import kr.hhplus.be.server.domain.reservation.dto.ReservationSerResponse
import java.time.LocalDateTime

data class ConcertReserveResponse(
    val concertDetailId: String,
    val concertName: String,
    val userId: String,
    val reserveId: String,
    val reserveTime: LocalDateTime,
) {

    companion object {

        fun from(serResponse: ReservationSerResponse): ConcertReserveResponse {
            return ConcertReserveResponse(
                concertDetailId = serResponse.concertDetail.uuid,
                concertName = serResponse.concertDetail.concert.name,
                userId = serResponse.member.uuid,
                reserveId = serResponse.reservation.uuid,
                reserveTime = serResponse.reservationTime
            )
        }
    }
}