package kr.hhplus.be.server.domain.queuecursor

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concert.Concert

@Entity
class QueueCursor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var point: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    val concert: Concert

): BaseEntity() {
}