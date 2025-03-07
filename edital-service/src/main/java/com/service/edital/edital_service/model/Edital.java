package com.service.edital.edital_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Key.class)
public class Edital {
    @Id
    private String title;
    @Id
    private String agency;
    private String description;
    private String pathToPdfFile;
    private LocalDate publicationDate;
    private LocalDate closingDate;
    private Boolean isClassified;
    private String classificationTrl;


}
