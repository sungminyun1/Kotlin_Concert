package kr.hhplus.be.server.application.scheduler

import kr.hhplus.be.server.application.facade.QueueFacade
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.concert.ConcertRepository
import kr.hhplus.be.server.domain.concert.ConcertStatus
import kr.hhplus.be.server.domain.queue.Queue
import kr.hhplus.be.server.domain.queue.QueueService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit


@Component
class QueuePollingScheduler(
    private val queueService: QueueService,
) {

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    fun polling(){
        val activeQueues = queueService.getAllActiveQueues()

        activeQueues.forEach {queueService.queuePoll(it, Companion.MAX_POLLING_SIZE)}
    }

    companion object {
        private const val MAX_POLLING_SIZE = 10
    }


}