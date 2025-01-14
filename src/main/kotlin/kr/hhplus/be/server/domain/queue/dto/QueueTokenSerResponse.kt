package kr.hhplus.be.server.domain.queue.dto

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member

data class QueueTokenSerResponse(
    val member: Member,
    val concert: Concert,
    val remainingUserNums: Long? = null,
    val token: String? = null
)
