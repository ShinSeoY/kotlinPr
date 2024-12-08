package com.my.kotlinPr.controller

import com.my.kotlinPr.dto.*
import com.my.kotlinPr.entity.Example
import com.my.kotlinPr.service.ExampleService
import com.my.kotlinPr.utils.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/example")
class ExampleController(
        @Autowired var exampleService: ExampleService
) {
    private val log = logger<ExampleController>()

    @GetMapping("coroutine/{id}")
    suspend fun coroutineFindOne(@PathVariable id: Long): String {
        return exampleService.coroutineFindOne(id)
    }

    @GetMapping("/dsl/{id}")
    fun findOneWithDsl(@PathVariable id: Long): ExampleResponseDto {
        return exampleService.findOneWithDsl(id)
    }

    @PostMapping("/dsl")
    fun findAllWithDslAndPageable(@RequestBody page: PageRequestDto ): ExampleResponseDto {
        return exampleService.findAllWithDslAndPageable(page)
    }

    @PostMapping("/sub")
    fun saveSub(@RequestBody exampleSub: ExampleSubRequestDto): ExampleSubResponseDto = exampleService.saveSub(exampleSub)

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ExampleResponseDto {
        return exampleService.findOne(id)
    }

    @GetMapping("")
    fun findAll(): ExampleResponseDto {
        return exampleService.findAll()
    }

    @PostMapping("")
    fun save(@RequestBody example: ExampleRequestDto): ExampleResponseDto = exampleService.save(example)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = ResponseEntity.ok().also { exampleService.delete(id) }

}