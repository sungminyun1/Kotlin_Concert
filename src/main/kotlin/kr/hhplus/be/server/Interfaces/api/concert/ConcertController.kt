package kr.hhplus.be.server.Interfaces.api.concert

import kr.hhplus.be.server.Interfaces.api.concert.dto.ConcertDetailResponse
import kr.hhplus.be.server.Interfaces.api.concert.dto.ConcertResponse
import kr.hhplus.be.server.Interfaces.api.concert.dto.Seat
import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/concert")
class ConcertController {

    @GetMapping("/list")
    fun getConcertList(): List<ConcertResponse>{
        return listOf(
            ConcertResponse(
                "concert1",
                "아이유 콘서트",
                LocalDateTime.of(2025,1,1,0,0,0),
                LocalDateTime.of(2025,1,3,0,0,0)),
            ConcertResponse(
                "concert2",
                "윤하 콘서트",
                LocalDateTime.of(2025,1,4,0,0,0),
                LocalDateTime.of(2025,1,5,0,0,0)),
        )
    }

    @GetMapping("/{concertId}")
    fun getConcertDetail(
        @PathVariable("concertId") concertId: String
    ): List<ConcertDetailResponse>{
        if(concertId == "error"){
            throw HplusNotfoundException("요청한 콘서트 정보를 찾을 수 없습니다")
        }
        return listOf(
            ConcertDetailResponse(
                "concert2", "윤하 콘서트", LocalDateTime.of(2025,1,4,0,0,0),
                listOf(
                    Seat(1,false),
                    Seat(2,true),
                    Seat(3,true),
                    Seat(4,true),
                    Seat(5,false),)
            ),
            ConcertDetailResponse(
                "concert2", "윤하 콘서트", LocalDateTime.of(2025,1,5,0,0,0),
                listOf(
                    Seat(1,true),
                    Seat(2,false),
                    Seat(3,false),
                    Seat(4,true),
                    Seat(5,false),)
            )
        )
    }
}