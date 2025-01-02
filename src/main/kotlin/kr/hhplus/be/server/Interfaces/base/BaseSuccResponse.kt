package kr.hhplus.be.server.Interfaces.base

import com.fasterxml.jackson.annotation.JsonInclude

data class BaseSuccResponse<T>(
    override val resCode: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T? = null
): BaseResponse {
    constructor(status: BaseResponseStatus) : this(resCode = status.code, data = null)
    constructor(status: BaseResponseStatus, data: T) : this(resCode = status.code, data = data)
}
