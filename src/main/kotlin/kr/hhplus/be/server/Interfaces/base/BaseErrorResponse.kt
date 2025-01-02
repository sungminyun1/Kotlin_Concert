package kr.hhplus.be.server.Interfaces.base

data class BaseErrorResponse(
    override val resCode: String,

    val errMsg: String? = "Unknown error"
): BaseResponse{
    constructor(status: BaseResponseStatus, err: Exception) : this(status.code, err.message)
}