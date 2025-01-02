package kr.hhplus.be.server.domain.queue

enum class QueueStatus(val status: String) {
    WAITING("WAITING"),
    DONE("DONE"),
}