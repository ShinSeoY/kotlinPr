package com.my.kotlinPr.listener

import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.stereotype.Component

@Component
class MemberEventListener {

    @SqsListener("member-event")
    fun listen(message: String) {
        println("received message ($message)")
    }
}