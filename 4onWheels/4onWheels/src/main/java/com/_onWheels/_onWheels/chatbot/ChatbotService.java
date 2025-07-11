package com._onWheels._onWheels.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {

    private final GeminiClient geminiClient;

    @Autowired
    public ChatbotService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String generateReply(String userMessage) {
        String context = "Only answer questions related to OnWheels data.";
        String prompt = context + "\n\nUser: " + userMessage + "\nBot:";

        return geminiClient.sendToGemini(prompt);
    }
}
