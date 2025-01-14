package kr.hhplus.be.server.infrastructure.concertdetail

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ConcertDetailJpaRepository: JpaRepository<ConcertDetail, Int> {

    @Query(
        """
            select cd from ConcertDetail cd join fetch cd.concert where cd.uuid = :uuid
        """
    )
    fun findByUuidWithJoin(uuid: String): ConcertDetail?
}