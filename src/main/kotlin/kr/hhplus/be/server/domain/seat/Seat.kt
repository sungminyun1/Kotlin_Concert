package kr.hhplus.be.server.domain.seat

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concertdetail.ConcertDetail

@Entity
class Seat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val seatNumber: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_detail_id")
    val concertDetail: ConcertDetail,

    var status: SeatStatus
): BaseEntity() {

    fun checkReservable(): Boolean{
        return status == SeatStatus.AVAIL
    }

    fun reserve(){
        status = SeatStatus.RESERVED
    }
}