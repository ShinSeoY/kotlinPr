package com.my.kotlinPr.controller

import com.my.kotlinPr.dto.s3.S3RequestDto
import com.my.kotlinPr.service.EtlService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/etl")
class EtlController(private val etlService: EtlService) {
    @PostMapping("/process-html")
    fun processHtml(@RequestBody s3RequestDto: S3RequestDto): ResponseEntity<String> {
        val processedKey = etlService.processHtml(s3RequestDto)
        return ResponseEntity.ok("Processed data saved to S3 with key: $processedKey")
    }
}