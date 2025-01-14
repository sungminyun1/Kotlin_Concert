package kr.hhplus.be.server.infrastructure.reservation

import kr.hhplus.be.server.domain.reservation.Reservation
import kr.hhplus.be.server.domain.reservation.ReservationRepository
import org.springframework.stereotype.Repository

@Repository
class ReservationRepositoryImpl(
    private val reservationJpaRepository: ReservationJpaRepository
): ReservationRepository {
    override fun save(reservation: Reservation): Reservation {
        return reservationJpaRepository.save(reservation)
    }
}