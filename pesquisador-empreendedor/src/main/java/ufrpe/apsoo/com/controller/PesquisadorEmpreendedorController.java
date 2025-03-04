package com.controller;

import com.model.PesquisadorEmpreendedor;
import com.service.PesquisadorEmpreendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pesquisador")
public class PesquisadorEmpreendedorController {

    private final PesquisadorEmpreendedorService service;

    public PesquisadorEmpreendedorController(PesquisadorEmpreendedorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PesquisadorEmpreendedor> create(@RequestBody PesquisadorEmpreendedor entity) {
        PesquisadorEmpreendedor saved = service.create(entity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PesquisadorEmpreendedor>> findAll() {
        List<PesquisadorEmpreendedor> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PesquisadorEmpreendedor> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PesquisadorEmpreendedor> update(@PathVariable Long id,
                                                          @RequestBody PesquisadorEmpreendedor entity) {
        PesquisadorEmpreendedor updated = service.update(id, entity);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
