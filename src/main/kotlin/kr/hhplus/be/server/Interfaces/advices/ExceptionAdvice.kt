package kr.hhplus.be.server.Interfaces.advices

import kr.hhplus.be.server.Interfaces.base.BaseErrorResponse
import kr.hhplus.be.server.Interfaces.base.BaseResponse
import kr.hhplus.be.server.Interfaces.base.BaseResponseStatus
import kr.hhplus.be.server.common.exceptions.HplusAlreadyReservedSeat
import kr.hhplus.be.server.common.exceptions.HplusIllegalArgumentException
import kr.hhplus.be.server.common.exceptions.HplusIllegalStateException
import kr.hhplus.be.server.common.exceptions.HplusNotfoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        HplusNotfoundException::class,
        HplusIllegalArgumentException::class
    )
    fun HplusBadRequestException(err: RuntimeException): BaseResponse {
        return BaseErrorResponse(BaseResponseStatus.ERROR, err)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(HplusIllegalStateException::class)
    fun HplusInternalErrorException(err: HplusIllegalStateException): BaseResponse {
        return BaseErrorResponse(BaseResponseStatus.ERROR, err)
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(HplusAlreadyReservedSeat::class)
    fun HplusAlreadyReservedSeatException(err: HplusAlreadyReservedSeat): BaseResponse {
        return BaseErrorResponse(BaseResponseStatus.ERROR, err)
    }
}