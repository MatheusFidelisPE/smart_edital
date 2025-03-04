package ufrpe.apsoo.com.controller;

import ufrpe.apsoo.com.model.Pesquisa;
import ufrpe.apsoo.com.service.PesquisaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pesquisa")
public class PesquisaController {

    private final PesquisaService service;

    public PesquisaController(PesquisaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pesquisa> create(@RequestBody Pesquisa entity) {
        Pesquisa saved = service.create(entity);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pesquisa>> findAll() {
        List<Pesquisa> list = service.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pesquisa> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pesquisa> update(@PathVariable Long id,
                                           @RequestBody Pesquisa entity) {
        Pesquisa updated = service.update(id, entity);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

