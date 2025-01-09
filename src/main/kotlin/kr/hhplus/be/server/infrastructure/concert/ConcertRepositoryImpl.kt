package kr.hhplus.be.server.infrastructure.concert

import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.concert.ConcertRepository
import kr.hhplus.be.server.domain.concert.ConcertStatus
import org.springframework.stereotype.Repository

@Repository
class ConcertRepositoryImpl(
    private val concertJpaRepository: ConcertJpaRepository
): ConcertRepository {
    override fun getByUuid(uuid: String): Concert {
        return concertJpaRepository.getByUuid(uuid)
    }

    override fun getAllWithQueueByStatus(status: ConcertStatus): List<Concert> {
        return concertJpaRepository.getAllWithQueueByStatus(status)
    }
}