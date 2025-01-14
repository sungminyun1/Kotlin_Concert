package kr.hhplus.be.server.domain.seat

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SeatService(
    private val seatRepository : SeatRepository,
) {

    @Transactional
    fun getSeatForUpdate(concertDetail: ConcertDetail, seatNum: Int): Seat {
        return seatRepository.getSeatByConcertDetailAndNumForUpdate(concertDetail, seatNum)
    }
}