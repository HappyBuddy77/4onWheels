package com._onWheels._onWheels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }
    
    @GetMapping("/chat-ui")
    public String chatbot() {
    	return "chatbot";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> chat(@RequestBody ChatRequest request) {
    	String response = chatbotService.generateReply(request.getMessage());
        return ResponseEntity.ok(response);
    }

    public static class ChatRequest {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
