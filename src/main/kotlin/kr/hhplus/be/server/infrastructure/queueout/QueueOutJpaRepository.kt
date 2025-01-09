package kr.hhplus.be.server.infrastructure.queueout

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queueout.QueueOut
import org.springframework.data.jpa.repository.JpaRepository

interface QueueOutJpaRepository: JpaRepository<QueueOut, Long> {

    fun findByMemberAndConcert(member: Member, concert: Concert): QueueOut?

}