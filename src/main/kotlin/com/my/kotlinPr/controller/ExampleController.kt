package com.my.kotlinPr.controller

import com.my.kotlinPr.dto.ExampleRequestDto
import com.my.kotlinPr.dto.ExampleResponseDto
import com.my.kotlinPr.entity.Court
import com.my.kotlinPr.service.CourtService
import com.my.kotlinPr.service.ExampleService
import com.my.kotlinPr.utils.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Objects

@RestController
@RequestMapping("/example")
class ExampleController(
        @Autowired var exampleService: ExampleService
){
    private val log = logger<ExampleController>()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ExampleResponseDto? {
        return exampleService.findOne(id)
    }

    @GetMapping("/")
    fun findAll(): List<ExampleResponseDto>{
        return exampleService.findAll()
    }

    @PostMapping("")
    fun save(@RequestBody example: ExampleRequestDto): ExampleResponseDto = exampleService.save(example)

    @DeleteMapping("")
    fun delete(id: Long) = ResponseEntity.ok().also { exampleService.delete(id) }

}