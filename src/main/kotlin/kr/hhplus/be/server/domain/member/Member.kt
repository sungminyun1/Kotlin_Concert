package kr.hhplus.be.server.domain.member

import jakarta.persistence.*
import kr.hhplus.be.server.domain.base.BaseEntity
import java.util.*

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 36)
    val uuid: String,

    val name: String
): BaseEntity() {
}