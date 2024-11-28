package com.my.kotlinPr.repository.standard

import com.my.kotlinPr.entity.Example
import org.springframework.data.jpa.repository.JpaRepository

interface ExampleRepository : JpaRepository<Example, Long>