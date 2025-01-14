package kr.hhplus.be.server.infrastructure.queueout

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queueout.QueueOut
import kr.hhplus.be.server.domain.queueout.QueueOutRepository
import org.springframework.stereotype.Repository

@Repository
class QueueOutRepositoryImpl(
    private val queueOutJpaRepository: QueueOutJpaRepository
): QueueOutRepository {
    override fun findByMemberAndConcert(member: Member, concert: Concert): QueueOut? {
        return queueOutJpaRepository.findByMemberAndConcert(member, concert)
    }

    override fun delete(queueOut: QueueOut) {
        queueOutJpaRepository.delete(queueOut)
    }
}