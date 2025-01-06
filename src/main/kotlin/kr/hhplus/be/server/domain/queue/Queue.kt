package kr.hhplus.be.server.domain.queue

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member

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

    @OneToOne
    @JoinColumn(name = "concert_id", nullable = false)
    val concert: Concert,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member

) :BaseEntity(){
}