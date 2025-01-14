package kr.hhplus.be.server.domain.reservation

interface ReservationRepository {

    fun save(reservation: Reservation): Reservation
}