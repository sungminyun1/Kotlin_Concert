package kr.hhplus.be.server.Interfaces.advices

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.Interfaces.base.BaseResponseStatus
import kr.hhplus.be.server.Interfaces.base.BaseSuccResponse
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@RestControllerAdvice
class BaseResponseAdvice() : ResponseBodyAdvice<Any> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return returnType.containingClass != ExceptionAdvice::class.java
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return when (body) {
            is String -> {
                val res = BaseSuccResponse(BaseResponseStatus.SUCCESS, body)
                try {
                    val objectMapper = ObjectMapper()
                    val stringRes = objectMapper.writeValueAsString(res)
                    response.headers.contentType = MediaType.APPLICATION_JSON
                    stringRes
                } catch (err: JsonProcessingException) {
                    throw RuntimeException("Failed to convert BaseResponse to JSON")
                }
            }
            else -> BaseSuccResponse(BaseResponseStatus.SUCCESS, body)
        }
    }
}
