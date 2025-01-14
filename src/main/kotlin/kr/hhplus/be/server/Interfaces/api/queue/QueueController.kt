package kr.hhplus.be.server.Interfaces.api.queue

import kr.hhplus.be.server.Interfaces.api.queue.dto.QueueResponse
import kr.hhplus.be.server.application.facade.QueueFacade
import kr.hhplus.be.server.domain.queue.QueueStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/queues")
class QueueController(
    private val queueFacade: QueueFacade
) {

    @GetMapping("/token/{userId}")
    fun getUserQueueToken(
        @PathVariable("userId") userId: String,
        concertId: String
    ): QueueResponse {
        return queueFacade.getUserToken(userId, concertId)
    }
}