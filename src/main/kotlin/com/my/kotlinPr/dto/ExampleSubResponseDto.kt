package com.my.kotlinPr.dto

import java.time.LocalDateTime

class ExampleSubResponseDto(
        code: String,
        result: String,
        error: String?,
        val exampleSubResponse: ExampleSubResponse?
) : BaseResponseDto(code, result, error) {
    companion object {
        fun success(response: ExampleSubResponse?) = ExampleSubResponseDto(
                code = "200",
                result = "success",
                error = null,
                exampleSubResponse = response
        )

        fun error(errorMessage: String) = ExampleSubResponseDto(
                code = "400",
                result = "error",
                error = errorMessage,
                exampleSubResponse = null
        )
    }
}

class ExampleSubResponse(val id: Long? = null,
                         val name: String? = null,
                         val exampleId: Long? = null,
                         val createdAt: LocalDateTime? = null,
                         val updatedAt: LocalDateTime? = null)