package com.viamatica.viamatica.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "personas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 60)
    @NotBlank
    private String names;

    @Column(name = "apellidos", nullable = false, length = 60)
    @NotBlank
    private String lastNames;

    @Column(name = "identificacion", nullable = false, unique = true, length = 10)
    @NotBlank
    @Size(min = 10, max = 10, message = "The identification must be 10 characters")
    @Pattern(regexp = "^[0-9]*$", message = "The identification must be only numbers")
    private String identification;

    @Column(name = "fechaNacimiento")
    private LocalDate birthDate;

}
