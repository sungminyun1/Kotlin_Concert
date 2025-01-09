package kr.hhplus.be.server.domain.queueslot

import jakarta.persistence.*
import kr.hhplus.be.server.domain.queue.Queue

@Entity
class QueueSlot(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var slot: Int = 1000,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    val queue: Queue
) {

    fun calcPollingSize(size: Int): Int {
        return slot.coerceAtMost(size)
    }
}