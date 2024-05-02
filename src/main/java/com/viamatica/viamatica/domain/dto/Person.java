package com.viamatica.viamatica.domain.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    private Long id;
    private String names;
    private String lastNames;
    private String identification;
    private LocalDate birthDate;
}
