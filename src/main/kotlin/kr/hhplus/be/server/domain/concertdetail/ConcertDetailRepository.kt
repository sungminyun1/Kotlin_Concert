package kr.hhplus.be.server.domain.concertdetail

interface ConcertDetailRepository {
    fun findByUuidWithJoin(uuid: String): ConcertDetail?
}