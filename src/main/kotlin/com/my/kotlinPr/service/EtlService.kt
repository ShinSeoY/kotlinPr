package com.my.kotlinPr.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.my.kotlinPr.dto.s3.S3RequestDto
import org.jsoup.Jsoup
import org.springframework.stereotype.Service

@Service
class EtlService(private val s3Service: S3Service,
                 private val objectMapper: ObjectMapper
) {

    fun processHtml(s3RequestDto: S3RequestDto): String? {
        val key = "raw-data/${s3RequestDto.fileName}"
        val processedKey = "processed-data/${s3RequestDto.fileName}"

        return extractInformation(s3Service.downloadFromS3(key))
        ?.let { processedContent ->
            s3Service.uploadHtmlContentToS3(processedContent, processedKey)
            processedKey
        }
    }

    fun extractInformation(htmlContent: String): String? {
        try {
            val document = Jsoup.parse(htmlContent)

            // __NEXT_DATA__ 스크립트 태그 찾기
            val scriptTag = document.getElementById("__NEXT_DATA__")

            scriptTag?.let {
                val jsonData = it.html()
                val data: Map<String, Any> = objectMapper.readValue(jsonData)

                @Suppress("UNCHECKED_CAST")
                return objectMapper.writeValueAsString(((((((data["props"] as? Map<String, Any>)
                        ?.get("pageProps") as? Map<String, Any>)
                        ?.get("dehydratedState") as? Map<String, Any>)
                        ?.get("queries") as? List<Map<String, Any>>)
                        ?.getOrNull(2)?.get("state") as? Map<String, Any>)
                        ?.get("data") as? Map<String, Any>)
                        ?.get("result") as? Map<String, Any>)
            }
            return null
        } catch (e: Exception) {
            throw RuntimeException("Failed to parse HTML content: ${e.message}")
        }
    }

}