package com.service;


import com.model.PesquisadorEmpreendedor;
import com.data.PesquisadorEmpreendedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PesquisadorEmpreendedorService {

    private final PesquisadorEmpreendedorRepository repository;

    public PesquisadorEmpreendedorService(PesquisadorEmpreendedorRepository repository) {
        this.repository = repository;
    }

    public PesquisadorEmpreendedor create(PesquisadorEmpreendedor entity) {
        return repository.save(entity);
    }

    public List<PesquisadorEmpreendedor> findAll() {
        return repository.findAll();
    }

    public Optional<PesquisadorEmpreendedor> findById(Long id) {
        return repository.findById(id);
    }

    public PesquisadorEmpreendedor update(Long id, PesquisadorEmpreendedor entity) {
        // Ensure the entity to update has the given id
        entity.setId(id);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
