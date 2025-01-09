package kr.hhplus.be.server.domain.activetoken

import kr.hhplus.be.server.common.utils.Util
import kr.hhplus.be.server.domain.queueout.QueueOut
import kr.hhplus.be.server.domain.queueout.QueueOutRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ActiveTokenService(
    private val queueOutRepository: QueueOutRepository,
    private val activeTokenRepository: ActiveTokenRepository,
) {

    @Transactional
    fun issueActiveToken(queueOut: QueueOut): ActiveToken {
        val token = ActiveToken.from(queueOut, Util::generateUUID)
        queueOutRepository.delete(queueOut)

        return activeTokenRepository.save(token)
    }

}