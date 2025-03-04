package com.service;

import com.model.Pesquisa;
import com.data.PesquisaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PesquisaService {

    private final PesquisaRepository repository;

    public PesquisaService(PesquisaRepository repository) {
        this.repository = repository;
    }

    public Pesquisa create(Pesquisa entity) {
        return repository.save(entity);
    }

    public List<Pesquisa> findAll() {
        return repository.findAll();
    }

    public Optional<Pesquisa> findById(Long id) {
        return repository.findById(id);
    }

    public Pesquisa update(Long id, Pesquisa entity) {
        entity.setId(id);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
