package com.my.kotlinPr.repository.custom

import com.my.kotlinPr.entity.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ExampleCustomRepository {
    fun findWithSubsByExampleId(id: Long): List<Example>

    fun findWithSubsByExampleIdWithPaging(pageable: Pageable): Page<Example>
}