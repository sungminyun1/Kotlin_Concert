package kr.hhplus.be.server.infrastructure.activetoken

import kr.hhplus.be.server.domain.activetoken.ActiveToken
import org.springframework.data.jpa.repository.JpaRepository

interface ActiveTokenJpaRepository: JpaRepository<ActiveToken, Long> {

}