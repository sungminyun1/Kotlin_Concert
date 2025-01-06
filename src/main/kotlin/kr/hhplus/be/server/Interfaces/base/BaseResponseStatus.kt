package kr.hhplus.be.server.Interfaces.base

enum class BaseResponseStatus(val code: String) {
    SUCCESS("SUCCESS"),
    UNAUTHORIZED("UNAUTHORIZED"),
    BAD_REQUEST("BAD_REQUEST"),
    ERROR("ERROR")
}
