package kr.hhplus.be.server.domain.queue

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member

interface QueueRepository {

    fun existsByMemberAndConcert(member: Member, concert: Concert): Boolean

    fun getByMemberAndConcert(member: Member, concert: Concert): Queue

    fun save(queue: Queue): Queue
}