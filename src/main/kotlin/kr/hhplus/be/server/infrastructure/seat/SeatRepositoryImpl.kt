package kr.hhplus.be.server.infrastructure.seat

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.seat.Seat
import kr.hhplus.be.server.domain.seat.SeatRepository
import org.springframework.stereotype.Repository

@Repository
class SeatRepositoryImpl(
    private val seatJpaRepository: SeatJpaRepository
): SeatRepository {
    override fun getSeatByConcertDetailAndNumForUpdate(concertDetail: ConcertDetail, seatNumb: Int): Seat {
        return seatJpaRepository
            .getSeatByConcertDetailAndNumForUpdate(concertDetail,seatNumb)
    }
}