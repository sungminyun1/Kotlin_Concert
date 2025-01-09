package kr.hhplus.be.server.domain.queuecursor

import kr.hhplus.be.server.domain.concert.Concert

interface QueueCursorRepository {
    fun getByConcert(concert: Concert): QueueCursor
}