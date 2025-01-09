package kr.hhplus.be.server.infrastructure.seat

import jakarta.persistence.LockModeType
import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.seat.Seat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface SeatJpaRepository: JpaRepository<Seat, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select s from Seat s where s.concertDetail = :concertDetail
        and s.seatNumber = :seatNumber
    """)
    fun getSeatByConcertDetailAndNumForUpdate(
        concertDetail: ConcertDetail,
        seatNumber: Int
    ): Seat
}