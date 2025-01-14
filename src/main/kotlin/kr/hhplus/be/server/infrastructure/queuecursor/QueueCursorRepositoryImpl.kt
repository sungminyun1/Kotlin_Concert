package kr.hhplus.be.server.infrastructure.queuecursor

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.queuecursor.QueueCursor
import kr.hhplus.be.server.domain.queuecursor.QueueCursorRepository
import org.springframework.stereotype.Repository

@Repository
class QueueCursorRepositoryImpl(
    private val queueCursorJpaRepository: QueueCursorJpaRepository
): QueueCursorRepository {
    override fun getByConcert(concert: Concert): QueueCursor {
        return queueCursorJpaRepository.getByConcert(concert)
    }
}