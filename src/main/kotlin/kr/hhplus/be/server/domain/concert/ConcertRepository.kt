package kr.hhplus.be.server.domain.concert

interface ConcertRepository {

    fun getByUuid(uuid: String): Concert

    fun getAllWithQueueByStatus(status: ConcertStatus): List<Concert>
}