package kr.hhplus.be.server.infrastructure.queuesequence

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.queuesequence.QueueSequence
import kr.hhplus.be.server.domain.queuesequence.QueueSequenceRepository
import org.springframework.stereotype.Repository

@Repository
class QueueSequenceRepositoryImpl(
    private val queueSequenceJpaRepository: QueueSequenceJpaRepository
): QueueSequenceRepository {
    override fun getByConcertForUpdate(concert: Concert): QueueSequence {
        return queueSequenceJpaRepository.getByConcertForUpdate(concert)
    }
}