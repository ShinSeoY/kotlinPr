package com.my.kotlinPr.dto

import java.time.LocalDateTime

class ExampleResponseDto(
        code: String = "200",
        result: String = "success",
        error: String? = null,
        val exampleResponse: ExampleResponse? = null
) : BaseResponseDto(code, result, error)

class ExampleResponse(val id: Long? = null,
                      val name: String? = null,
                      val numberArray: Array<Int>? = null,
                      val stringArray: Array<String>? = null,
                      val jsonData: Map<String, Any>? = null,
                      val createdAt: LocalDateTime? = null,
                      val updatedAt: LocalDateTime? = null)