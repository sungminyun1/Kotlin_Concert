package kr.hhplus.be.server.infrastructure.queue

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queue.Queue
import org.springframework.data.jpa.repository.JpaRepository

interface QueueJpaRepository: JpaRepository<Queue, Long> {

    fun existsByMemberAndConcert(member: Member, concert: Concert): Boolean

    fun getByMemberAndConcert(member: Member, concert: Concert): Queue

}