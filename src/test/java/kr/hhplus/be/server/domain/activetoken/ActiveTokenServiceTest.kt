package kr.hhplus.be.server.domain.activetoken

import kr.hhplus.be.server.TestSupporter
import kr.hhplus.be.server.common.utils.Util
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queueout.QueueOut
import kr.hhplus.be.server.domain.queueout.QueueOutRepository
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository
import kr.hhplus.be.server.infrastructure.member.MemberJpaRepository
import kr.hhplus.be.server.infrastructure.queueout.QueueOutJpaRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.time.LocalDateTime

class ActiveTokenServiceTest: TestSupporter(){

    @Autowired
    lateinit var activeTokenService: ActiveTokenService

    @Autowired
    lateinit var queueOutRepository: QueueOutRepository

    @Autowired
    lateinit var queueOutJpaRepository: QueueOutJpaRepository

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var concertJpaRepository: ConcertJpaRepository

    @Test
    @DisplayName("토큰을 발급할때는 queueOut 데이터를 기반으로 발급하고 queueOut은 삭제한다")
    fun testIssueToken(){
        //given
        val concert = Concert(
            uuid = Util.generateUUID(32),
            name = "test concert",
            seatCapacity = 50,
            endDate = LocalDateTime.of(2025,1,2,0,0,0),
            startDate = LocalDateTime.of(2025,1,1,0,0,0),
        )
        val member = Member(
            uuid = Util.generateUUID(32),
            name = "test member",
        )
        val queueOut = QueueOut(
            concert = concert,
            member = member,
        )
        memberJpaRepository.save(member)
        concertJpaRepository.save(concert)
        queueOutJpaRepository.save(queueOut)

        //when
        val activeToken = activeTokenService.issueActiveToken(queueOut)

        //then
        assertThat(activeToken.id).isNotNull
        assertThat(activeToken.token.length).isEqualTo(ActiveToken.TOKEN_LENGTH)
        assertThat(queueOutJpaRepository.findById(activeToken.id!!)).isEmpty()
    }
}