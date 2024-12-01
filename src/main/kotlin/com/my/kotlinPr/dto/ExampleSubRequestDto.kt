package com.my.kotlinPr.dto

import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.entity.ExampleSub

data class ExampleSubRequestDto(
        val name: String? = null,
        val exampleId: Long? = null
) {
    fun toEntity(example: Example) = ExampleSub(
            id = 0,
            name = this.name.orEmpty(),
            example = example
    )
}