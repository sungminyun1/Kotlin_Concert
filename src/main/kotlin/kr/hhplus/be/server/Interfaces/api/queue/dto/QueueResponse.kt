package kr.hhplus.be.server.Interfaces.api.queue.dto

import com.fasterxml.jackson.annotation.JsonInclude
import kr.hhplus.be.server.domain.queue.QueueStatus

data class QueueResponse(
    val userId: String,
    val status: QueueStatus,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val remainingNumber: Long? = null
)
