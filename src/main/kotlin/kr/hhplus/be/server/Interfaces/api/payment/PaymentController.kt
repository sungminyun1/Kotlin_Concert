package kr.hhplus.be.server.Interfaces.api.payment

import kr.hhplus.be.server.Interfaces.api.payment.dto.PaymentRequest
import kr.hhplus.be.server.Interfaces.api.payment.dto.PaymentResponse
import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("api/v1/payment")
class PaymentController {

    @PostMapping
    fun payment(
        @RequestBody request: PaymentRequest
    ): PaymentResponse {
        if(request.reservationId == "error") throw HplusNotfoundException("결제하려는 예약 정보를 찾을 수 없습니다")
        return PaymentResponse("reservationId", "userId", LocalDateTime.now())
    }
}