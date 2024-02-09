package com.example.websocket.controllers

import com.example.websocket.controllers.pojo.ChatMessage
import com.example.websocket.service.ChatMessageSender
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class ChatController(
    private val chatMessageSender: ChatMessageSender
) {
    @MessageMapping("/chat/in/{channel}")
    fun receiveChatMessage(
        @DestinationVariable channel: String,
        message: ChatMessage
    ) {
        chatMessageSender.receiveChatMessage(channel, message)
    }
}
