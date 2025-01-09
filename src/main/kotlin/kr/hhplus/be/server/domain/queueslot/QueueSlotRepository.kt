package kr.hhplus.be.server.domain.queueslot

import kr.hhplus.be.server.domain.queue.Queue

interface QueueSlotRepository {
    fun getByQueueForUpdate(queue: Queue): QueueSlot
}