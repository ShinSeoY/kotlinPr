package com.my.kotlinPr.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.GetObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.nio.charset.StandardCharsets

@Service
class S3Service(
        @Value("\${spring.cloud.aws.s3.bucket}")
        private val bucket: String,
        private val s3Client: S3Client
) {

    fun uploadHtmlContentToS3(htmlContent: String, fileName: String): String {
        val putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .contentType("application/json")
                .build()

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromString(htmlContent, StandardCharsets.UTF_8)
        )

        return "https://$bucket.s3.amazonaws.com/$fileName"
    }

    fun downloadFromS3(fileName: String): String {
        val getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build()

        return s3Client.getObject(getObjectRequest).use { response ->
            response.readAllBytes().toString(StandardCharsets.UTF_8)
        }
    }

}