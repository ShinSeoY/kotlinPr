package com.my.kotlinPr.dto

import java.time.LocalDateTime

class ExampleResponseDto(
        code: String,
        result: String,
        error: String?,
        index: Int,
        size: Int,
        val exampleResponse: List<ExampleResponse>?
) : BaseResponseDto(code, result, error, index, size) {
    companion object {
        fun success(index: Int, size: Int, response: List<ExampleResponse>?) = ExampleResponseDto(
                code = "200",
                result = "success",
                error = null,
                index = index,
                size = size,
                exampleResponse = response
        )

        fun success(response: List<ExampleResponse>?) = ExampleResponseDto(
                code = "200",
                result = "success",
                error = null,
                index = 0,
                size = 0,
                exampleResponse = response
        )

        fun error(errorMessage: String) = ExampleResponseDto(
                code = "400",
                result = "error",
                error = errorMessage,
                index = 0,
                size = 0,
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
