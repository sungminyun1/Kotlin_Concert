package kr.hhplus.be.server.domain.reservation.dto

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.seat.Seat

data class ReservationSerRequest(
    val member: Member,
    val concertDetail: ConcertDetail,
    val seat: Seat
)
