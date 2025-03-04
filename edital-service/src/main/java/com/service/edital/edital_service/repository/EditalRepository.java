package com.service.edital.edital_service.repository;

import com.service.edital.edital_service.model.Edital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditalRepository extends JpaRepository<Edital, Long> {

}
