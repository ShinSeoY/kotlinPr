package com.my.kotlinPr.repository

import com.my.kotlinPr.entity.Example
import org.springframework.data.jpa.repository.JpaRepository

interface ExampleRepository: JpaRepository<Example, Long> {
}