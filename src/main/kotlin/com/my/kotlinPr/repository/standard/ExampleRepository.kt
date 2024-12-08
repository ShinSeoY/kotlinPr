package com.my.kotlinPr.repository.standard

import com.my.kotlinPr.entity.Example
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ExampleRepository : JpaRepository<Example, Long> {

    fun findNameById(id: Long): Example

    fun findCreatedAtById(id: Long): Example

}