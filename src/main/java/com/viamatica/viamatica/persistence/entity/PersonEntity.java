package com.viamatica.viamatica.persistence.entity;

import jakarta.persistence.*;
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
    private String names;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String lastNames;

    @Column(name = "identificacion", nullable = false, unique = true, length = 10)
    private String identification;

    @Column(name = "fechaNacimiento")
    private LocalDate birthDate;

}
