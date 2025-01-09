package kr.hhplus.be.server.domain.queue.dto

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member

data class QueueTokenSerRequest(
    val member: Member,
    val concert: Concert
) {
}