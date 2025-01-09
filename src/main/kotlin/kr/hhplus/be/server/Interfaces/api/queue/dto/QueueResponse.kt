package kr.hhplus.be.server.Interfaces.api.queue.dto

import com.fasterxml.jackson.annotation.JsonInclude
import kr.hhplus.be.server.domain.queue.QueueStatus

data class QueueResponse(
    val userId: String,
    val concertId: String,
    val status: QueueStatus,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val remainingNumber: Long? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val token: String? = null
) {
    companion object {

        fun responseWaiting(userId: String, concertId: String, remainingNumber: Long): QueueResponse {
            return QueueResponse(
                userId = userId,
                concertId = concertId,
                status = QueueStatus.WAITING,
                remainingNumber = remainingNumber,
            )
        }

        fun responseDone(userId: String, concertId: String, token: String): QueueResponse {
            return QueueResponse(
                userId = userId,
                concertId = concertId,
                status = QueueStatus.DONE,
                token = token
            )
        }
    }
}
