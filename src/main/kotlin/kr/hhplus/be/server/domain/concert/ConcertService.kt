package kr.hhplus.be.server.domain.concert

import kr.hhplus.be.server.common.exceptions.HplusIllegalArgumentException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConcertService(
    private val concertRepository: ConcertRepository
) {

    @Transactional(readOnly = true)
    fun getConcertByUuid(uuid: String): Concert {
        if (uuid.isBlank()) throw HplusIllegalArgumentException("유효하지 않은 Concert uuid 입니다")

        return concertRepository.getByUuid(uuid)
    }

    @Transactional
    fun reserveConcert(
        concertDetailId: String,
        userId: String,
        seatNum: Int
    ){
        val concert = concertRepository.getByUuid(concertDetailId)

    }
}