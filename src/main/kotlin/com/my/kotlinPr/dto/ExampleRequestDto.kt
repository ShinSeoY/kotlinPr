package com.my.kotlinPr.dto

import com.my.kotlinPr.entity.Example

data class ExampleRequestDto (
    val name: String? = null,
    val numberArray: List<Int>? = null,
    val stringArray: List<String>? = null,
    val jsonData: Map<String, Any>? = null,
){

    fun toEntity() = Example(
            id = 0,
            name = this.name.orEmpty(),
            numberArray = this.numberArray?.toTypedArray() ?: emptyArray(),
            stringArray = this.stringArray?.toTypedArray() ?: emptyArray(),
            jsonData = this.jsonData ?: hashMapOf()
    )

}