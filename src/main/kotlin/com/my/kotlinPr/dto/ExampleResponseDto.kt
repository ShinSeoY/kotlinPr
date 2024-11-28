package com.my.kotlinPr.dto

import java.time.LocalDateTime

class ExampleResponseDto(
        code: String,
        result: String,
        error: String?,
        val exampleResponse: List<ExampleResponse>?
) : BaseResponseDto(code, result, error) {
    companion object {
        fun success(response: List<ExampleResponse>?) = ExampleResponseDto(
                code = "200",
                result = "success",
                error = null,
                exampleResponse = response
        )

        fun error(errorMessage: String) = ExampleResponseDto(
                code = "400",
                result = "error",
                error = errorMessage,
                exampleResponse = null
        )
    }
}

data class ExampleResponse(val id: Long? = null,
                           val name: String? = null,
                           val numberArray: Array<Int>? = null,
                           val stringArray: Array<String>? = null,
                           val jsonData: Map<String, Any>? = null,
                           val createdAt: LocalDateTime? = null,
                           val updatedAt: LocalDateTime? = null,
                           val exampleSubResponseList: List<ExampleSubResponse>?)
