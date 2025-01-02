package kr.hhplus.be.server.Interfaces.api.member

import kr.hhplus.be.server.Interfaces.api.member.dto.PointResponse
import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/member")
class MemberController {

   @GetMapping("/{userId}/point")
   fun getMemberPoint(
       @PathVariable("userId") userId: String,
   ): PointResponse {
       if(userId == "error") throw HplusNotfoundException("에러 응답 예시")
       return PointResponse("userId", 1000, LocalDateTime.now())
   }
}