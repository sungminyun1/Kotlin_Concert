package kr.hhplus.be.server.domain.concertdetail

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import kr.hhplus.be.server.domain.concert.Concert
import java.time.LocalDateTime

@Entity
class ConcertDetail(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    val uuid: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    val concert: Concert,

    val date: LocalDateTime
): BaseEntity(){
}