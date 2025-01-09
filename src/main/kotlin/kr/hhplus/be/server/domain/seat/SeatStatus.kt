package kr.hhplus.be.server.domain.seat

enum class SeatStatus(val status: String) {
    AVAIL("AVAIL"),
    DONE("DONE"),
    RESERVED("RESERVED"),
}