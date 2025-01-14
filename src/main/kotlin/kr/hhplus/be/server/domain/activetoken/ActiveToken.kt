package kr.hhplus.be.server.domain.activetoken

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concert.Concert
import kr.hhplus.be.server.domain.member.Member
import kr.hhplus.be.server.domain.queueout.QueueOut
import java.time.LocalDateTime

@Entity
class ActiveToken(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    val token: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val concert: Concert,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    var lastUse: LocalDateTime? = null

): BaseEntity() {

    companion object {

        const val TOKEN_LENGTH = 32

        fun from(queueOut: QueueOut, tokenGenerator: (Int) -> String): ActiveToken {
            return ActiveToken(
                token = tokenGenerator(TOKEN_LENGTH),
                concert = queueOut.concert,
                member = queueOut.member,
            )
        }
    }
}