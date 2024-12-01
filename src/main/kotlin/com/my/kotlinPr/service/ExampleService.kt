package com.my.kotlinPr.service

import com.my.kotlinPr.dto.*
import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.entity.ExampleSub
import com.my.kotlinPr.entity.QExample
import com.my.kotlinPr.entity.QExampleSub
import com.my.kotlinPr.repository.custom.ExampleCustomRepository
import com.my.kotlinPr.repository.standard.ExampleRepository
import com.my.kotlinPr.repository.standard.ExampleSubRepository
import com.my.kotlinPr.utils.logger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExampleService(
        private val exampleRepository: ExampleRepository,
        private val exampleCustomRepository: ExampleCustomRepository,
        private val exampleSubRepository: ExampleSubRepository
) {

    private val log = logger<ExampleService>()

    val qExample = QExample.example
    val qExampleSub = QExampleSub.exampleSub

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

    fun findOneWithDsl(id: Long): ExampleResponseDto =
            try {
                ExampleResponseDto.success(exampleCustomRepository.findWithSubsByExampleId(id).map { it.toResponseDto() })
            } catch (e: Exception) {
                ExampleResponseDto.error(e.message ?: "")
            }


    fun findAllWithDslAndPageable(page: PageRequestDto): ExampleResponseDto =
            try {
                exampleCustomRepository.findWithSubsByExampleIdWithPaging(page.toPageable())
                        .map { it.toResponseDto() }
                        .let {
                            ExampleResponseDto.success(page.index, page.size, it.toList())
                        }
            } catch (e: Exception) {
                ExampleResponseDto.error(e.message ?: "")
            }

    fun saveSub(exampleSubRequestDto: ExampleSubRequestDto): ExampleSubResponseDto =
            try {
                val example = exampleRepository.findByIdOrNull(exampleSubRequestDto.exampleId)
                        ?: throw Error("example is not exist")
                // 아래에 있는 맨 마지막줄이 리턴됨
                ExampleSubResponseDto.success(exampleSubRepository.save(exampleSubRequestDto.toEntity(example)).toResponseDto())
            } catch (e: Exception) {
                ExampleSubResponseDto.error(e.message ?: "")
            }


    fun findAll(): ExampleResponseDto = ExampleResponseDto.success(exampleRepository.findAll().map { it.toResponseDto() })


    fun findOne(id: Long): ExampleResponseDto =
            exampleRepository.findByIdOrNull(id)
                    ?.let {
                        ExampleResponseDto.success(listOf(it.toResponseDto()))
                    } ?: ExampleResponseDto.error("empty data")


    fun save(exampleRequestDto: ExampleRequestDto): ExampleResponseDto =
            ExampleResponseDto.success(listOf(exampleRepository.save(exampleRequestDto.toEntity()).toResponseDto()))


    fun delete(id: Long) = exampleRepository.deleteById(id)

    fun Example.toResponseDto(): ExampleResponse =
            ExampleResponse(
                    id = this.id,
                    name = this.name,
                    numberArray = this.numberArray.toList(),
                    stringArray = this.stringArray.toList(),
                    jsonData = this.jsonData,
                    createdAt = this.createdAt,
                    updatedAt = this.updatedAt,
                    exampleSubResponseList = this.exampleSubs?.map { it.toResponseDto() }
            )

    fun ExampleSub.toResponseDto(): ExampleSubResponse =
            ExampleSubResponse(
                    id = this.id,
                    name = this.name,
                    exampleId = this.example.id,
                    createdAt = this.createdAt,
                    updatedAt = this.updatedAt
            )
}