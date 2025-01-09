package kr.hhplus.be.server.infrastructure.activetoken

import kr.hhplus.be.server.domain.activetoken.ActiveToken
import kr.hhplus.be.server.domain.activetoken.ActiveTokenRepository
import org.springframework.stereotype.Repository


@Repository
class ActiveTokenRepositoryImpl(
    private val activeTokenJpaRepository: ActiveTokenJpaRepository
) : ActiveTokenRepository {
    override fun save(token: ActiveToken): ActiveToken {
        return activeTokenJpaRepository.save(token)
    }
}