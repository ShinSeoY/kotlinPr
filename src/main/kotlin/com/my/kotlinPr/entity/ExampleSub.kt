package com.my.kotlinPr.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table
@EntityListeners(AuditingEntityListener::class)
class ExampleSub(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column
        var name: String,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "exampleId")
        var example: Example,

        @CreatedDate
        var createdAt: LocalDateTime? = null,

        @LastModifiedDate
        var updatedAt: LocalDateTime? = null
)