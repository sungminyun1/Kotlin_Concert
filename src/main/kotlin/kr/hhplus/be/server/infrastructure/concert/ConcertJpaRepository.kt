package kr.hhplus.be.server.infrastructure.concert

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.concert.ConcertStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConcertJpaRepository: JpaRepository<Concert, Long> {

    fun getByUuid(uuid: String): Concert

    @Query("""
        select c from Concert c join fetch c.queue where c.status = :status
    """)
    fun getAllWithQueueByStatus(status: ConcertStatus): List<Concert>
}