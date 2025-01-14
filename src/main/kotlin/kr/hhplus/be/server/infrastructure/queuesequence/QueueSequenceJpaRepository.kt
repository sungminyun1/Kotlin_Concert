package kr.hhplus.be.server.infrastructure.queuesequence

import jakarta.persistence.LockModeType
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.queuesequence.QueueSequence
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface QueueSequenceJpaRepository: JpaRepository<QueueSequence, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query( """
        select qs from QueueSequence qs where qs.concert = :concert
    """)
    fun getByConcertForUpdate(concert: Concert): QueueSequence
}