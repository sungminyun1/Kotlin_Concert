package kr.hhplus.be.server.domain.queueout

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueueOutService(
    private val queueOutRepository: QueueOutRepository,
) {

    @Transactional(readOnly = true)
    fun findByMemberAndConcert(
        member: Member,
        concert: Concert,
    ): QueueOut? {
        return queueOutRepository.findByMemberAndConcert(member, concert)
    }

}