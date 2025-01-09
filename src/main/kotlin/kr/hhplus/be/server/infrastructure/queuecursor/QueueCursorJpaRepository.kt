package kr.hhplus.be.server.infrastructure.queuecursor

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.queuecursor.QueueCursor
import org.springframework.data.jpa.repository.JpaRepository

interface QueueCursorJpaRepository: JpaRepository<QueueCursor, Long> {

    fun getByConcert(concert: Concert): QueueCursor
}