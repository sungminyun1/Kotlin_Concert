package kr.hhplus.be.server.domain.concert

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import java.time.LocalDateTime
import java.util.*

@Entity
class Concert(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true, length = 36)
    val uuid: String,

    val name: String,

    val price: Int = 0,

    val seatCapacity: Int,

    val startDate: LocalDateTime,

    val endDate: LocalDateTime,
) :BaseEntity(){
}