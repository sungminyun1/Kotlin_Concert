package kr.hhplus.be.server.infrastructure.member

import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.member.MemberRepository
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    private val memberJpaRepository : MemberJpaRepository
): MemberRepository {
    override fun getByUuid(uuid: String): Member {
        return memberJpaRepository.getByUuid(uuid)
    }
}