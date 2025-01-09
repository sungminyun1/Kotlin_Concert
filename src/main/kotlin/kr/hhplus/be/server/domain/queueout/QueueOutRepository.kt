package kr.hhplus.be.server.domain.queueout

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member

interface QueueOutRepository {

    fun findByMemberAndConcert(member: Member, concert: Concert): QueueOut?

    fun delete(queueOut: QueueOut)
}