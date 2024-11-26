package com.my.kotlinPr.entity

import com.vladmihalcea.hibernate.type.array.IntArrayType
import com.vladmihalcea.hibernate.type.array.StringArrayType
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
        var id: Long? = 0,

        @Column
        var name: String,

        @Type(IntArrayType::class)
        @Column(columnDefinition = "integer[]")
        val numberArray: Array<Int>?,

        @Type(StringArrayType::class)
        @Column(columnDefinition = "text[]")
        val stringArray: Array<String>?,

        @Type(JsonType::class)
        @Column(columnDefinition = "jsonb")
        val jsonData: Map<String, Any>?,

        @OneToMany(mappedBy = "example")
        var exampleSubs: MutableSet<ExampleSub>? = null,

        @CreatedDate
        var createdAt: LocalDateTime? = null,

        @LastModifiedDate
        var updatedAt: LocalDateTime? = null
)