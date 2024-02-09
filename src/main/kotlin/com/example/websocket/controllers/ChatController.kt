package com.example.websocket.controllers

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/chat/in/{channel}")
    @SendTo("/chat/out/{channel}")
    fun greeting(
        @DestinationVariable channel: String,
        message: GreetingMessage
    ): GreetingMessage {
        return GreetingMessage("hello world");
    }

}

data class GreetingMessage(
    val content: String
)


