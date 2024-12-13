package com.my.kotlinPr.controller

import io.awspring.cloud.sqs.operations.SqsTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/messages")
class PublisherController(
        private val sqsTemplate: SqsTemplate,
) {

    @PostMapping("")
    fun sendMessage(@RequestBody body: Map<String, String>) {

        val content = body["content"] ?: ""
        sqsTemplate.send {
            it.queue("member-event")
                    .payload(content)
        }
    }
}