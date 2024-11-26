package com.my.kotlinPr.dto

import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.entity.ExampleSub

class ExampleSubRequestDto(
        val name: String,
        val exampleId: Long
) {
    fun toEntity(example: Example) = ExampleSub(
            name = this.name,
            example = example
    )
}