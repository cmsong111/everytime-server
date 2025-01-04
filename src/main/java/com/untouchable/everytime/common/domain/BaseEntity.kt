package com.untouchable.everytime.common.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import java.time.Instant
import org.hibernate.annotations.SoftDelete
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@SoftDelete(columnName = "is_deleted")
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity(
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
)
