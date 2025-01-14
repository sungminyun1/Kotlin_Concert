package kr.hhplus.be.server.domain.reservation

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.seat.Seat
import java.time.LocalDateTime

@Entity
class Reservation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    val uuid: String,

    @OneToOne(fetch = FetchType.LAZY)
    val seat: Seat,

    @OneToOne(fetch = FetchType.LAZY)
    val concertDetail: ConcertDetail,

    @OneToOne(fetch = FetchType.LAZY)
    val member: Member,

    val price: Int,

    val expireAt: LocalDateTime

    ): BaseEntity(){
        companion object {
            const val EXPIRE_MINUTES = 5

            fun calcExpireAt(
                dateTime: LocalDateTime,
            ): LocalDateTime {
                return dateTime.plusMinutes(EXPIRE_MINUTES.toLong())
            }
        }
}