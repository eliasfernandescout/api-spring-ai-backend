package api_ai_backend.controller;

import api_ai_backend.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/ai")
    String chat(@RequestBody String userInput) {
        return chatService.generateResponse(userInput);
    }

    @PostMapping(value = "/ai-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestBody String userInput) {
        return chatService.generateStreamResponse(userInput);
    }
}