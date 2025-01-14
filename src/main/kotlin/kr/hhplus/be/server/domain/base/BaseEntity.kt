package kr.hhplus.be.server.domain.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null
}