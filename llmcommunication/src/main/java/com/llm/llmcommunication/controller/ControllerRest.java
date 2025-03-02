package com.llm.llmcommunication.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping
@RestController
public class ControllerRest {

    @Value("${token}")
    private String token;

    @GetMapping("/token")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok(token);
    }
}
