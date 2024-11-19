package com.my.kotlinPr.entity

import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table
class Example(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @Column
        var name: String,

        @Type(JsonType::class)
        @Column(columnDefinition = "integer[]")
        val numberArray: Array<Int>,

        @Type(JsonType::class)
        @Column(columnDefinition = "text[]")
        val stringArray: Array<String>,

        @Type(JsonType::class)
        @Column(columnDefinition = "json")
        val jsonData: Map<String, Any>,

        @CreatedDate
        var createdAt: LocalDateTime? = null,

        @LastModifiedDate
        var updatedAt: LocalDateTime? = null
)