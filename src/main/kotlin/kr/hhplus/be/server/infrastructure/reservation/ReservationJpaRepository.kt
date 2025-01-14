package kr.hhplus.be.server.infrastructure.reservation

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.reservation.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationJpaRepository: JpaRepository<Reservation, Long> {

    fun findByConcertDetailAndMember(concertDetail: ConcertDetail, member: Member): Reservation?
}