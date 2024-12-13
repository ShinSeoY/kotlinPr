package com.my.kotlinPr.listener

import com.my.kotlinPr.utils.logger
import io.awspring.cloud.sqs.annotation.SqsListener
import io.awspring.cloud.sqs.listener.acknowledgement.Acknowledgement
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.model.Message


@Component
class MemberEventListener {

    private val log = logger<MemberEventListener>()
    private val MAX_RETRY_ATTEMPTS = 3

    @SqsListener(value = ["member-event"])
    fun listen(message: Message, payload: Any, @Headers headers: MessageHeaders, acknowledgement: Acknowledgement) {
        try {
            println("received message headers ($headers)")
            println("received message body (${message.body()})")
//            throw Exception("my-errororor")
            println("received message id (${message.messageId()})")
            acknowledgement.acknowledge()
        } catch (e: Exception) {
            handleException(headers, message, acknowledgement)
        }
    }

    fun handleException(headers: MessageHeaders, message: Message, acknowledgement: Acknowledgement) {
        val messageId = message.messageId()
        val retryCnt = getRetryCount(headers)
        log.error("retryCnt : {}", retryCnt)

        if (retryCnt < MAX_RETRY_ATTEMPTS) {
            // ack 하지 않음 자동으로 큐로 다시 돌아감
            log.error("message id : $messageId, retry count : $retryCnt")
        } else {
            log.error("message id : $messageId, retry count : $retryCnt. Moving to DLQ.")
            moveToDeadLetterQueue(message)
            acknowledgement.acknowledge()
        }
    }

    // SQS 메시지 속성에서 재시도 횟수를 가져옴
    private fun getRetryCount(headers: MessageHeaders): Int {
        return headers["Sqs_Msa_ApproximateReceiveCount"]?.toString()?.toInt() ?: 0
    }

    private fun moveToDeadLetterQueue(message: Message) {
        try {
            // DLQ로 메시지 전송 로직
//            sendMessageToDLQ(message)
            log.info("Message {} moved to DLQ", message.messageId())
        } catch (e: java.lang.Exception) {
//            log.error("Failed to move message to DLQ: {}", message.getMessageId(), e)
        }
    }
}