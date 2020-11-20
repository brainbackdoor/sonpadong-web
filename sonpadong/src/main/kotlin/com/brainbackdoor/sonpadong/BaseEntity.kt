package com.brainbackdoor.sonpadong

import support.RandomValueIdGenerator
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity<A> protected constructor(
) {
    @CreatedDate
    @Column(nullable = false)
    var createdDate: LocalDateTime? = null
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    var updatedDate: LocalDateTime? = null
        protected set
}

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AutoIncrementEntity<A> protected constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
) : BaseEntity<A>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AutoIncrementEntity<*>

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class RandomIdEntity<A> protected constructor(
        @Id
        @GenericGenerator(name = "random_id", strategy = "support.RandomValueIdGenerator")
        @GeneratedValue(generator = "random_id")
        @Column(length = RandomValueIdGenerator.ID_LENGTH, nullable = false)
        val id: String = ""
) : BaseEntity<A>() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RandomIdEntity<*>

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
