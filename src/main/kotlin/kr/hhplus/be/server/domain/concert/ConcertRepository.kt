package kr.hhplus.be.server.domain.concert

interface ConcertRepository {

    fun getByUuid(uuid: String): Concert
}