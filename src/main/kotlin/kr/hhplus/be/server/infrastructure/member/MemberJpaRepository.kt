package kr.hhplus.be.server.infrastructure.member

import kr.hhplus.be.server.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository: JpaRepository<Member, Long> {

    fun getByUuid(uuid: String): Member
}