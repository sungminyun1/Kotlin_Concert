package kr.hhplus.be.server.domain.queuesequence

import jakarta.persistence.*
import kr.hhplus.be.server.domain.concert.Concert

@Entity
class QueueSequence(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var sequence: Long,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id", nullable = false)
    val concert: Concert
) {

    fun increase(){
        sequence++
    }
}