package com.llm.llmcommunication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class CommunicationController {


    @Value("${url_gemini}")
    private String urlGemini;
    @Value("${token}")
    private String token;


    public String classifyEdital(String text) {
        String url = urlGemini+token;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{\n" +
                        "  \"contents\": [{\n" +
                        "    \"parts\":[{\"text\": \"%s\"}]\n" +
                        "    }]\n" +
                        "}", text)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.statusCode());
        System.out.println(response.body());
        return response.body();
    }



}
