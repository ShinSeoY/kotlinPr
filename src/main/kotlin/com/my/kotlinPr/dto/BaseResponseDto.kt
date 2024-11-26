package com.my.kotlinPr.dto

open class BaseResponseDto(
       open val code: String,
       open val result: String,
       open val error: String?
)