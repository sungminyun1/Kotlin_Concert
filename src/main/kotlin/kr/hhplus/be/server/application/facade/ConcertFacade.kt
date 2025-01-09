package kr.hhplus.be.server.application.facade

import kr.hhplus.be.server.Interfaces.api.concert.dto.ConcertReserveRequest
import kr.hhplus.be.server.Interfaces.api.concert.dto.ConcertReserveResponse
import kr.hhplus.be.server.domain.concert.ConcertService
import kr.hhplus.be.server.domain.concertdetail.ConcertDetailService
import kr.hhplus.be.server.domain.member.MemberService
import kr.hhplus.be.server.domain.reservation.ReservationService
import kr.hhplus.be.server.domain.reservation.dto.ReservationSerRequest
import kr.hhplus.be.server.domain.seat.SeatService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConcertFacade(
    private val concertDetailService: ConcertDetailService,
    private val seatService: SeatService,
    private val memberService: MemberService,
    private val reservationService: ReservationService,
) {

    @Transactional
    fun reserveConcert(
        request: ConcertReserveRequest
    ): ConcertReserveResponse {
        val concertDetail = concertDetailService.getConcertDetailWithJoinByUuid(request.concertDetailId)
        val seat = seatService.getSeatForUpdate(concertDetail, request.seat)
        val member = memberService.getMemberByUuid(request.userId)

        val serRequest = ReservationSerRequest(
            member, concertDetail, seat
        )

        val serResponse = reservationService.reserveSeat(serRequest)

        return ConcertReserveResponse.from(serResponse)

    }
}