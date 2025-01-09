package kr.hhplus.be.server.domain.queue

import kr.hhplus.be.server.TestSupporter
import kr.hhplus.be.server.common.utils.Util
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queue.dto.QueueTokenSerRequest
import kr.hhplus.be.server.domain.queuecursor.QueueCursor
import kr.hhplus.be.server.domain.queuesequence.QueueSequence
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository
import kr.hhplus.be.server.infrastructure.member.MemberJpaRepository
import kr.hhplus.be.server.infrastructure.queue.QueueJpaRepository
import kr.hhplus.be.server.infrastructure.queuecursor.QueueCursorJpaRepository
import kr.hhplus.be.server.infrastructure.queuesequence.QueueSequenceJpaRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class QueueServiceTest: TestSupporter() {

    @Autowired
    lateinit var queueService: QueueService

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Autowired
    lateinit var concertJpaRepository: ConcertJpaRepository

    @Autowired
    lateinit var queueJpaRepository: QueueJpaRepository

    @Autowired
    lateinit var queueCursorJpaRepository: QueueCursorJpaRepository

    @Autowired
    lateinit var queueSequenceJpaRepository: QueueSequenceJpaRepository

    @Test
    @DisplayName("유저가 이미 큐에 들어가 있다면 ture 아니면 false")
    fun testCheckAlreadyUserEnqueued(){
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
        val member2 = Member(
            uuid = Util.generateUUID(32),
            name = "test member2",
        )
        memberJpaRepository.save(member)
        memberJpaRepository.save(member2)
        concertJpaRepository.save(concert)
        val queue = Queue(
            sequence = 1,
            concert = concert,
            member = member
        )
        queueJpaRepository.save(queue)
        val request = QueueTokenSerRequest(member, concert)
        val request2 = QueueTokenSerRequest(member2, concert)

        //when
        val exist = queueService.checkUserAlreadyEnqueued(request)
        val nonExist = queueService.checkUserAlreadyEnqueued(request2)

        //then
        assertThat(exist).isTrue()
        assertThat(nonExist).isFalse()
    }

    @Test
    @DisplayName("큐에 대기중인 유저의 남은 대기 순서를 구한다")
    fun testGetRemainingNum(){
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
        memberJpaRepository.save(member)
        concertJpaRepository.save(concert)
        val queue = Queue(
            sequence = 100,
            concert = concert,
            member = member
        )
        queueJpaRepository.save(queue)

        val queueCursor = QueueCursor(
            point = 20,
            concert = concert
        )
        queueCursorJpaRepository.save(queueCursor)

        val request = QueueTokenSerRequest(member, concert)

        //when
        val res = queueService.getRemainQueueUsersNumbers(request)

        //then
        assertThat(res.remainingUserNums).isEqualTo(queue.getRemainingUsersNumber(queueCursor))
    }

    @Test
    @DisplayName("대기열에 유저를 넣는다")
    fun testEnqueueMember(){
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
        memberJpaRepository.save(member)
        concertJpaRepository.save(concert)
        val sequence = QueueSequence(
            sequence = 100,
            concert = concert
        )
        queueSequenceJpaRepository.save(sequence)

        val queueCursor = QueueCursor(
            point = 20,
            concert = concert
        )
        queueCursorJpaRepository.save(queueCursor)

        val request = QueueTokenSerRequest(member, concert)

        //when
        val res = queueService.enqueueMember(request)

        //then
        assertThat(sequence.sequence).isEqualTo(101)
        val queue = queueJpaRepository.getByMemberAndConcert(member,concert)
        assertThat(queue).isNotNull()
        assertThat(queue.sequence).isEqualTo(101)
        assertThat(res.remainingUserNums).isEqualTo(queue.getRemainingUsersNumber(queueCursor))
    }
}