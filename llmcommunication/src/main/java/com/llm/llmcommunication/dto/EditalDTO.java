package com.llm.llmcommunication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record EditalDTO(String title,
                        String description,
                        String agency,
                        String pathToPdfFile,
                        @JsonFormat(pattern = "dd/MM/yyyy")
                        LocalDate publicationDate,
                        @JsonFormat(pattern = "dd/MM/yyyy")
                        LocalDate closingDate) {


}