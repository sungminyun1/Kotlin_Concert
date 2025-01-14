package kr.hhplus.be.server.domain.queuesequence

import kr.hhplus.be.server.domain.concert.Concert

interface QueueSequenceRepository {

    fun getByConcertForUpdate(concert: Concert): QueueSequence
}