package com.my.kotlinPr.repository.standard

import com.my.kotlinPr.entity.ExampleSub
import org.springframework.data.jpa.repository.JpaRepository

interface ExampleSubRepository : JpaRepository<ExampleSub, Long> {
}