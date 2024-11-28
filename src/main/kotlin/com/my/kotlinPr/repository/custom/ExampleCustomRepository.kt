package com.my.kotlinPr.repository.custom

import com.my.kotlinPr.entity.Example

interface ExampleCustomRepository {
    fun findWithSubsByExampleId(id: Long): List<Example>

//    fun findWithPaging(pageable: Pageable): Page<Example>
}