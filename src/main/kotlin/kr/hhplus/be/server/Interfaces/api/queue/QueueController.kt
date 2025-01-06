package kr.hhplus.be.server.Interfaces.api.queue

import kr.hhplus.be.server.Interfaces.api.queue.dto.QueueResponse
import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import kr.hhplus.be.server.domain.queue.QueueStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/queues")
class QueueController {

    @GetMapping("/token/{userId}")
    fun getUserQueueToken(
        @PathVariable("userId") userId: String
    ): QueueResponse {
        if(userId == "error") throw HplusNotfoundException("에러 응답 예시")
        else if(userId == "done") return QueueResponse("userId", QueueStatus.DONE)
        return QueueResponse("userId", QueueStatus.WAITING, 1000)
    }
}