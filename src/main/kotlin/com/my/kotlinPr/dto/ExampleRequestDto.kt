package com.my.kotlinPr.dto

import com.my.kotlinPr.entity.Example

class ExampleRequestDto (
    val name: String,
    val numberArray: Array<Int>?,
    val stringArray: Array<String>?,
    val jsonData: Map<String, Any>?,
){
    fun toEntity() = Example(
            name = this.name,
            numberArray = this.numberArray,
            stringArray = this.stringArray,
            jsonData = this.jsonData
    )
}