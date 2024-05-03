package com.viamatica.viamatica.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank(message = "The name is required")
    private String name;

    @Column(name = "es_activo")
    private boolean isActive;

    @PrePersist
    protected void onCreate() {
        this.isActive = true;
    }
}
