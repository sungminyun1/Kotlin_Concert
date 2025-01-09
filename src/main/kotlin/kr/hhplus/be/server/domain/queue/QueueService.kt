package kr.hhplus.be.server.domain.queue

import kr.hhplus.be.server.domain.concert.ConcertRepository
import kr.hhplus.be.server.domain.concert.ConcertStatus
import kr.hhplus.be.server.domain.queue.dto.QueueTokenSerRequest
import kr.hhplus.be.server.domain.queue.dto.QueueTokenSerResponse
import kr.hhplus.be.server.domain.queuecursor.QueueCursorRepository
import kr.hhplus.be.server.domain.queuesequence.QueueSequenceRepository
import kr.hhplus.be.server.domain.queueslot.QueueSlotRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueueService(
    private val queueRepository: QueueRepository,
    private val queueCursorRepository: QueueCursorRepository,
    private val queueSequenceRepository: QueueSequenceRepository,
    private val concertRepository: ConcertRepository,
    private val queueSlotRepository: QueueSlotRepository
) {

    @Transactional(readOnly = true)
    fun checkUserAlreadyEnqueued(
        request: QueueTokenSerRequest
    ): Boolean {
        return queueRepository.existsByMemberAndConcert(request.member, request.concert)
    }

    @Transactional(readOnly = true)
    fun getRemainQueueUsersNumbers (
        request: QueueTokenSerRequest
    ): QueueTokenSerResponse {
        val current = queueRepository.getByMemberAndConcert(request.member, request.concert)
        val cursor = queueCursorRepository.getByConcert(request.concert)

        val remains = current.getRemainingUsersNumber(cursor)

        return QueueTokenSerResponse(
            member = request.member,
            concert = request.concert,
            remainingUserNums = remains
        )
    }

    @Transactional
    fun enqueueMember(
        request: QueueTokenSerRequest
    ): QueueTokenSerResponse {
        val sequence = queueSequenceRepository.getByConcertForUpdate(request.concert)
        sequence.increase()

        val queue = Queue(
            sequence = sequence.sequence,
            concert = request.concert,
            member = request.member,
        )
        queueRepository.save(queue)
        val cursor = queueCursorRepository.getByConcert(request.concert)

        val remains = queue.getRemainingUsersNumber(cursor)

        return QueueTokenSerResponse(
            member = request.member,
            concert = request.concert,
            remainingUserNums = remains
        )
    }

    @Transactional(readOnly = true)
    fun getAllActiveQueues() : List<Queue> {
        return concertRepository.getAllWithQueueByStatus(ConcertStatus.IN_PROGRESS)
            .mapNotNull { it.queue }
    }

    @Transactional
    fun queuePoll(queue: Queue ,size: Int){
        val queueSlot = queueSlotRepository.getByQueueForUpdate(queue)
        val pollingSize = queueSlot.calcPollingSize(size)

        val queues = queueRepository.getQueuesForPolling(pollingSize)
    }
}