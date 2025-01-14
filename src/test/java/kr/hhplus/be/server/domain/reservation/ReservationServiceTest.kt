package kr.hhplus.be.server.domain.reservation

import kr.hhplus.be.server.TestSupporter
import kr.hhplus.be.server.common.exceptions.HplusAlreadyReservedSeat
import kr.hhplus.be.server.common.utils.Util
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.reservation.dto.ReservationSerRequest
import kr.hhplus.be.server.domain.seat.Seat
import kr.hhplus.be.server.domain.seat.SeatStatus
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository
import kr.hhplus.be.server.infrastructure.concertdetail.ConcertDetailJpaRepository
import kr.hhplus.be.server.infrastructure.member.MemberJpaRepository
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository
import kr.hhplus.be.server.infrastructure.seat.SeatJpaRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationServiceTest: TestSupporter() {

    @Autowired
    private lateinit var reservationService: ReservationService

    @Autowired
    private lateinit var reservationJpaRepository: ReservationJpaRepository

    @Autowired
    private lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    private lateinit var concertJpaRepository: ConcertJpaRepository

    @Autowired
    private lateinit var concertDetailJpaRepository: ConcertDetailJpaRepository

    @Autowired
    private lateinit var seatJpaRepository: SeatJpaRepository

    @Test
    @DisplayName("콘서트 세부 정보를 기반으로 좌석을 예약한다")
    fun testReserveSeat(){
        //given
        val member = Member(
            uuid = Util.generateUUID(32),
            name = "test member"
        )
        memberJpaRepository.save(member)

        val concert = Concert(
            uuid = Util.generateUUID(32),
            name = "test concert",
            price = 5000,
            seatCapacity = 10,
            startDate = LocalDateTime.of(2021, 6, 21, 1, 0),
            endDate = LocalDateTime.of(2021, 6, 22, 13, 0),
        )
        concertJpaRepository.save(concert)

        val concertDetail = ConcertDetail(
            uuid = Util.generateUUID(32),
            concert = concert,
            date = LocalDateTime.of(2021, 6, 22, 13, 0),
        )
        concertDetailJpaRepository.save(concertDetail)

        val seat = Seat(
            seatNumber = 1,
            concertDetail = concertDetail,
            status = SeatStatus.AVAIL
        )
        seatJpaRepository.save(seat)

        val request = ReservationSerRequest(
            member = member,
            concertDetail = concertDetail,
            seat = seat
        )

        //when
        val result = reservationService.reserveSeat(request)

        //then
        assertThat(reservationJpaRepository.findByConcertDetailAndMember(
            concertDetail = concertDetail, member = member
        )).isNotNull
        assertThat(seat.status).isEqualTo(SeatStatus.RESERVED)
    }

    @DisplayName("좌석이 예약 가능 상태가 아니라면 HplusAlreadyReservedSeat 에러가 발생한다")
    fun testReserveSeatError(){
        //given
        val member = Member(
            uuid = Util.generateUUID(32),
            name = "test member"
        )
        memberJpaRepository.save(member)

        val concert = Concert(
            uuid = Util.generateUUID(32),
            name = "test concert",
            price = 5000,
            seatCapacity = 10,
            startDate = LocalDateTime.of(2021, 6, 21, 1, 0),
            endDate = LocalDateTime.of(2021, 6, 22, 13, 0),
        )
        concertJpaRepository.save(concert)

        val concertDetail = ConcertDetail(
            uuid = Util.generateUUID(32),
            concert = concert,
            date = LocalDateTime.of(2021, 6, 22, 13, 0),
        )
        concertDetailJpaRepository.save(concertDetail)

        val seat = Seat(
            seatNumber = 1,
            concertDetail = concertDetail,
            status = SeatStatus.RESERVED
        )
        seatJpaRepository.save(seat)

        val request = ReservationSerRequest(
            member = member,
            concertDetail = concertDetail,
            seat = seat
        )

        //when then
        assertThatThrownBy { reservationService.reserveSeat(request)  }
            .isInstanceOfAny(HplusAlreadyReservedSeat::class.java)
    }
}