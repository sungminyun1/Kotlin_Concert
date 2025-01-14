package kr.hhplus.be.server.domain.queue

import jakarta.persistence.*
import kr.hhplus.be.server.common.exceptions.HplusIllegalStateException
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queuecursor.QueueCursor

@Entity
@Table(
    name = "queue",
    indexes = [
        Index(name = "idx_member_concert", columnList = "member_id, concert_id")
    ]
)
class Queue(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    val sequence: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    val concert: Concert,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member

) :BaseEntity(){

    fun getRemainingUsersNumber(
        cursor: QueueCursor
    ): Long {
        val remains = sequence - cursor.point

        if(remains < 0){
            throw HplusIllegalStateException("남은 유저수가 음수 입니다. sequence = $sequence , cursor = ${cursor.point}")
        }

        return remains
    }
}