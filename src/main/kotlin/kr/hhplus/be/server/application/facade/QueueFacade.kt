package kr.hhplus.be.server.application.facade

import kr.hhplus.be.server.Interfaces.api.queue.dto.QueueResponse
import kr.hhplus.be.server.domain.activetoken.ActiveTokenService
import kr.hhplus.be.server.domain.concert.ConcertService
import kr.hhplus.be.server.domain.member.MemberService
import kr.hhplus.be.server.domain.queue.QueueService
import kr.hhplus.be.server.domain.queue.dto.QueueTokenSerRequest
import kr.hhplus.be.server.domain.queueout.QueueOutService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueueFacade(
    private val memberService: MemberService,
    private val concertService: ConcertService,
    private val queueService: QueueService,
    private val queueOutService: QueueOutService,
    private val activeTokenService: ActiveTokenService
) {

    @Transactional
    fun getUserToken(
        userId: String,
        concertId: String
    ): QueueResponse{
        val member = memberService.getMemberByUuid(userId)
        val concert = concertService.getConcertByUuid(concertId)

        val request = QueueTokenSerRequest(member, concert)

        if(queueService.checkUserAlreadyEnqueued(request)){
            val serResponse = queueService.getRemainQueueUsersNumbers(request)
            return QueueResponse.responseWaiting(
                serResponse.member.uuid,
                serResponse.concert.uuid,
                serResponse.remainingUserNums!!
            )
        }

        val queueOut = queueOutService.findByMemberAndConcert(request.member, request.concert)
        if(queueOut == null){
            val serResponse = queueService.enqueueMember(request)
            return QueueResponse.responseWaiting(
                serResponse.member.uuid,
                serResponse.concert.uuid,
                serResponse.remainingUserNums!!
            )
        }

        val activeToken = activeTokenService.issueActiveToken(queueOut)

        return QueueResponse.responseDone(userId, concertId, activeToken.token)
    }
}