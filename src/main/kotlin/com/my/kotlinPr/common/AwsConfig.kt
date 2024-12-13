package com.my.kotlinPr.common

//import com.amazonaws.auth.AWSStaticCredentialsProvider
//import com.amazonaws.auth.BasicAWSCredentials
//import com.amazonaws.services.s3.AmazonS3Client
//import com.amazonaws.services.s3.AmazonS3ClientBuilder

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory
import io.awspring.cloud.sqs.listener.SqsContainerOptionsBuilder
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementOrdering
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.time.Duration


@Configuration
class AwsConfig(
//        @Value("\${cloud.aws.credentials.access-key}")
//        val accessKey: String,
//        @Value("\${cloud.aws.credentials.secret-key}")
//        val secretKey: String,
//        @Value("\${cloud.aws.region.static}")
//        val region: String
) {
//    @Bean
//    fun amazonS3Client(): AmazonS3Client {
//        val credentials = BasicAWSCredentials(accessKey, secretKey)
//
//        return AmazonS3ClientBuilder
//                .standard()
//                .withRegion(region)
//                .withCredentials(AWSStaticCredentialsProvider(credentials))
//                .build() as AmazonS3Client
//    }

    @Bean
    fun defaultSqsListenerContainerFactory(sqsAsyncClient: SqsAsyncClient): SqsMessageListenerContainerFactory<Any>? {
        return SqsMessageListenerContainerFactory
                .builder<Any>()
                .configure { options: SqsContainerOptionsBuilder ->
                    options
                            .acknowledgementMode(AcknowledgementMode.MANUAL) // 수동으로 ack설정을 해줘야함. 수신받은 메시지를 큐에서 자동제거하고싶으면 ALWAYS
                            .acknowledgementInterval(Duration.ofSeconds(3)) // 3초마다 처리된 메시지의 수신 확인을 SQS에 전송
                            .acknowledgementThreshold(5) // 5개의 메시지가 처리되면, 그 때 수신 확인을 sqs에 전송 (위의 duration과는 or 관계)
                            .acknowledgementOrdering(AcknowledgementOrdering.ORDERED) // 메시지의 수신 확인을 보낼 때의 순서 지정
                }
                .sqsAsyncClient(sqsAsyncClient)
                .build()
    }

    // 메시지 발송을 위한 SQS 템플릿 설정 (Sender 쪽)
//    @Bean
//    fun sqsTemplate(): SqsTemplate? {
//        return SqsTemplate.newTemplate(sqsAsyncClient())
//    }
}