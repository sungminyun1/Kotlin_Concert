package kr.hhplus.be.server.domain.concertdetail

import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import org.springframework.stereotype.Service

@Service
class ConcertDetailService(
    private val concertDetailRepository: ConcertDetailRepository,
) {

    fun getConcertDetailWithJoinByUuid(uuid: String): ConcertDetail {
        return concertDetailRepository.findByUuidWithJoin(uuid)
            ?: throw HplusNotfoundException("콘서트 세부정보를 찾을 수 없습니다. id: $uuid")
    }

}