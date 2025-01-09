package kr.hhplus.be.server.infrastructure.queue

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queue.Queue
import kr.hhplus.be.server.domain.queue.QueueRepository
import org.springframework.stereotype.Repository

@Repository
class QueueRepositoryImpl(
    private val queueJpaRepository : QueueJpaRepository
): QueueRepository {
    override fun existsByMemberAndConcert(member: Member, concert: Concert): Boolean {
        return queueJpaRepository.existsByMemberAndConcert(member, concert)
    }

    override fun getByMemberAndConcert(member: Member, concert: Concert): Queue {
        return queueJpaRepository.getByMemberAndConcert(member, concert)
    }

    override fun save(queue: Queue): Queue {
        return queueJpaRepository.save(queue)
    }
}