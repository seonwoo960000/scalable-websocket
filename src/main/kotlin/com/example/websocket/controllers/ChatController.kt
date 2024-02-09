package com.example.websocket.controllers

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: GreetingMessage): GreetingMessage {
        Thread.sleep(1000); // simulated delay
        return GreetingMessage("hello world");
    }

}

data class GreetingMessage(
    val content: String
)


