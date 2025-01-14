package kr.hhplus.be.server.domain.seat

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail

interface SeatRepository {

    fun getSeatByConcertDetailAndNumForUpdate(concertDetail: ConcertDetail, seatNumb: Int): Seat
}