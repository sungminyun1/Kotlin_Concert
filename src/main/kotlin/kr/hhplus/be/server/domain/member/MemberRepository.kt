package kr.hhplus.be.server.domain.member

interface MemberRepository {

    fun getByUuid(uuid: String): Member
}