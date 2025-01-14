package kr.hhplus.be.server.domain.reservation

import kr.hhplus.be.server.common.exceptions.HplusAlreadyReservedSeat
import kr.hhplus.be.server.common.utils.Util
import kr.hhplus.be.server.domain.reservation.dto.ReservationSerRequest
import kr.hhplus.be.server.domain.reservation.dto.ReservationSerResponse
import kr.hhplus.be.server.domain.seat.Seat
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
) {

    @Transactional
    fun reserveSeat(
        request: ReservationSerRequest
    ): ReservationSerResponse{
        if(!request.seat.checkReservable()){
            throw HplusAlreadyReservedSeat("이미 예약된 좌석입니다")
        }
        request.seat.reserve()

        val reserveTime = LocalDateTime.now()

        val reservation = Reservation(
            uuid = Util.generateUUID(32),
            seat = request.seat,
            concertDetail = request.concertDetail,
            member = request.member,
            price = request.concertDetail.concert.price,
            expireAt = Reservation.calcExpireAt(reserveTime),
        )
        reservationRepository.save(reservation)

        return ReservationSerResponse(
            reservation = reservation,
            member = request.member,
            concertDetail = request.concertDetail,
            reservationTime = reserveTime
        )
    }
}