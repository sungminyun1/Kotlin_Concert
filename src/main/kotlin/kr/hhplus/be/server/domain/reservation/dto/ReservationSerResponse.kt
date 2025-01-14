package kr.hhplus.be.server.domain.reservation.dto

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.reservation.Reservation
import java.time.LocalDateTime

data class ReservationSerResponse(
    val concertDetail: ConcertDetail,
    val member: Member,
    val reservation: Reservation,
    val reservationTime: LocalDateTime
)
