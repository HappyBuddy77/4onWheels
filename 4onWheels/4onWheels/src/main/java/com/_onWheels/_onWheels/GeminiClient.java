package com._onWheels._onWheels;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient {

    private final Client client;

    public GeminiClient() {
        // Load .env explicitly
        Dotenv dotenv = Dotenv.configure()
                              .filename(".env")
                              .directory(System.getProperty("user.dir"))
                              .ignoreIfMissing()
                              .load();

        // Get the API key from .env, fallback to environment variables if needed
        String apiKey = dotenv.get("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = System.getenv("GEMINI_API_KEY");
        }
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException(
                "API key must be provided either in .env as GEMINI_API_KEY or as environment variable.");
        }

        // Build Client explicitly passing the apiKey
        this.client = Client.builder()
                            .apiKey(apiKey)
                            .build();
    }

    public String sendToGemini(String prompt) {
        try {
            GenerateContentResponse response =
                    client.models.generateContent("gemini-2.5-flash", prompt, null);
            return response.text();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
