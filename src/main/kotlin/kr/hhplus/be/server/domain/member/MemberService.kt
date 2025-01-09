package kr.hhplus.be.server.domain.member

import kr.hhplus.be.server.common.exceptions.HplusIllegalArgumentException
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun getMemberByUuid(uuid: String?): Member{
        if(uuid.isNullOrBlank()) throw HplusIllegalArgumentException("유효하지 않은 member uuid 입니다")

        return memberRepository.getByUuid(uuid)
    }
}