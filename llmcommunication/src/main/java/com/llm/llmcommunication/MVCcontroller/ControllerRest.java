package com.llm.llmcommunication.MVCcontroller;
import com.llm.llmcommunication.Facade;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RequestMapping
@RestController
public class ControllerRest {

    @Value("${token}")
    private String token;
    private final Facade facade;

    public ControllerRest(Facade facade) {
        this.facade = facade;
    }

    @GetMapping("/token")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok(token);
    }

    @GetMapping("/editais")
    public ResponseEntity<?> editais() {
        return ResponseEntity.ok(facade.getAllEditais());
    }

    @GetMapping(value = "/pdf", produces = "application/pdf")
    public ResponseEntity<byte[]> pdf() {
        byte[] pdf;
        try {
             pdf = facade.getPdfFile("catalisa ictjr 2024", "Sebrae-PE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(pdf);
    }
    @GetMapping(value="/teste", produces = "application/json")
    public ResponseEntity<?> teste(@RequestParam(name = "title", required = true) String title, @RequestParam(name = "agency", required = true) String agency) {
        return ResponseEntity.ok(facade.classifyEdital(title, agency));
    }
    @GetMapping(value="/new", produces = "application/json")
    public ResponseEntity<?> testeNew() {
        return ResponseEntity.ok(facade.testNew());
    }

}
