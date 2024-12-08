package com.my.kotlinPr.controller

import com.my.kotlinPr.dto.s3.S3RequestDto
import com.my.kotlinPr.service.S3Service
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URL

@RestController
@RequestMapping("/s3")
class S3Controller(private val s3Service: S3Service) {

    @PostMapping("/upload")
    fun uploadHtml(@RequestBody s3RequestDto: S3RequestDto): ResponseEntity<String> {
        val htmlContent = URL(s3RequestDto.url).readText() // HTML 다운로드
        val key = "raw-data/${s3RequestDto.fileName}"
        val url = s3Service.uploadHtmlContentToS3(htmlContent, key)
        return ResponseEntity.ok("HTML saved to S3 with : $url")
    }

}