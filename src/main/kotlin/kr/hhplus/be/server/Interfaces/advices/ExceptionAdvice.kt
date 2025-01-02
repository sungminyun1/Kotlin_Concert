package kr.hhplus.be.server.Interfaces.advices

import kr.hhplus.be.server.Interfaces.base.BaseErrorResponse
import kr.hhplus.be.server.Interfaces.base.BaseResponse
import kr.hhplus.be.server.Interfaces.base.BaseResponseStatus
import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HplusNotfoundException::class)
    fun HplusNotFoundException(err: HplusNotfoundException): BaseResponse {
        return BaseErrorResponse(BaseResponseStatus.ERROR, err)
    }
}