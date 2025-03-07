package com.llm.llmcommunication.adapters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Component
public class LlmClient {


    @Value("${url_gemini}")
    private String urlGemini;
    @Value("${token}")
    private String token;


    public String classifyEdital(String prompt, byte[] pdf) {
        String url = String.format("%s%s", urlGemini, token);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonFormatter(this.getPrompt(), pdf)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    private String jsonFormatter(String prompt, byte[] pdf) {
        String encodedPdf = Base64.getEncoder().encodeToString(pdf);
        String jsonBody = String.format("{\n" +
                "      \"contents\": [{\n" +
                "        \"parts\":[\n" +
                "          {\"inline_data\": {\"mime_type\": \"application/pdf\", \"data\": \"%s\"}},\n" +
                "          {\"text\": \"%s\"}\n" +
                "        ]\n" +
                "      }]\n" +
                "    }", encodedPdf, prompt);
        return jsonBody;
    }

    private String getPrompt() {
        String prompt = "Classifique o seguinte edital de acordo com o nível de maturidade tecnológica (TRL), considerando sua descrição e características. Responda apenas com o número do TRL correspondente. Responda com 'TRL 1' ou 'TRL 2' apenas isso. Responda apenas o TRL <numero> que o edital está contemplando.";
        return prompt;
    }
}
