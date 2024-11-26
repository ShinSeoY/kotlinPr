package com.my.kotlinPr.service

import com.my.kotlinPr.dto.BaseResponseDto
import com.my.kotlinPr.dto.ExampleRequestDto
import com.my.kotlinPr.dto.ExampleResponse
import com.my.kotlinPr.dto.ExampleResponseDto
import com.my.kotlinPr.entity.Court
import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.repository.CourtRepository
import com.my.kotlinPr.repository.ExampleRepository
import org.apache.catalina.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExampleService(
        private val exampleRepository: ExampleRepository
) {

//    1. also를 사용한 로깅과 체이닝
//    fun saveUser(user: User) = user
//            .also { log.info("Saving user: ${it.name}") }
//            .also { validate(it) }
//            .let { userRepository.save(it) }
//
//    2. let을 사용한 null 체크와 변환
//    fun getUserEmail(id: Long): String? {
//        return userRepository.findById(id)
//                ?.let { "${it.name}<${it.email}>" }
//    }

    fun findAll(): ExampleResponseDto {
        return ExampleResponseDto.success(exampleRepository.findAll().map { it.toResponseDto() })
    }

    fun findOne(id: Long): ExampleResponseDto {
        return exampleRepository.findByIdOrNull(id)
                ?.let{
                    ExampleResponseDto.success(listOf(it.toResponseDto()))
                } ?: ExampleResponseDto.error("empty data")
    }

    fun save(exampleRequestDto: ExampleRequestDto): ExampleResponseDto {
        return ExampleResponseDto.success(listOf(exampleRepository.save(exampleRequestDto.toEntity()).toResponseDto()))
    }

    fun delete(id: Long) = exampleRepository.deleteById(id)

    fun Example.toResponseDto(): ExampleResponse =
            ExampleResponse(
                    id = this.id,
                    name = this.name,
                    numberArray = this.numberArray,
                    stringArray = this.stringArray,
                    jsonData = this.jsonData,
                    createdAt = this.createdAt,
                    updatedAt = this.updatedAt

            )
}