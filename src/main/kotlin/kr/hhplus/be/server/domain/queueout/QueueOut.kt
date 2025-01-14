package kr.hhplus.be.server.domain.queueout

import jakarta.persistence.*
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member

@Entity
@Table(
    name = "queue_out",
    indexes = [
        Index(name = "idx_member_concert", columnList = "member_id, concert_id")
    ]
)
class QueueOut(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    val concert: Concert,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member
) {
}