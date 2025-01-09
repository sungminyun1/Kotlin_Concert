package kr.hhplus.be.server.infrastructure.concertdetail

import kr.hhplus.be.server.domain.concertdetail.ConcertDetail
import kr.hhplus.be.server.domain.concertdetail.ConcertDetailRepository
import org.springframework.stereotype.Repository

@Repository
class ConcertDetailRepositoryImpl(

): ConcertDetailRepository {
    override fun findByUuidWithJoin(uuid: String): ConcertDetail? {
        TODO("Not yet implemented")
    }
}