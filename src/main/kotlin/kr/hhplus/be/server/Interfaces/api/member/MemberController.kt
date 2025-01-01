package kr.hhplus.be.server.Interfaces.api.member

import kr.hhplus.be.server.Interfaces.api.member.dto.PointResponse
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
       @PathVariable("userId") userName: String,
   ): PointResponse {
       return PointResponse("userId", 1000, LocalDateTime.now())
   }
}