package com.service.edital.edital_service.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Key implements Serializable {
    private String title;
    private String agency;
}
