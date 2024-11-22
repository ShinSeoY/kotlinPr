package com.my.kotlinPr.repository

import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.entity.ExampleSub
import org.springframework.data.jpa.repository.JpaRepository

interface ExampleSubRepository: JpaRepository<ExampleSub, Long> {
}