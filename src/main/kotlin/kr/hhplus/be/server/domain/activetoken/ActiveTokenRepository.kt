package kr.hhplus.be.server.domain.activetoken

interface ActiveTokenRepository {

    fun save(token: ActiveToken): ActiveToken
}