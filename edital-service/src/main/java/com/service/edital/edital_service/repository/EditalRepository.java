package com.service.edital.edital_service.repository;

import com.service.edital.edital_service.model.Edital;
import com.service.edital.edital_service.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditalRepository extends JpaRepository<Edital, Key> {

    Optional<Edital> findByTitleAndAgency(String title, String agency);

    Optional<Edital> findPathToPdfFileByTitleAndAgency(String title, String agency);

    @Query(value = "SELECT * FROM edital where is_classified = false", nativeQuery = true)
    Optional<List<Edital>> findnewers();
}
