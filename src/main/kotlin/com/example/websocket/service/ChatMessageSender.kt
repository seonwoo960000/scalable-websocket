package com.example.websocket.service

import com.example.websocket.controllers.pojo.ChatMessage
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service

@Service
class ChatMessageSender {
    @SendTo("/chat/out/{channel}")
    fun receiveChatMessage(
        @DestinationVariable channel: String,
        message: ChatMessage
    ): ChatMessage {
        return message
    }
}



