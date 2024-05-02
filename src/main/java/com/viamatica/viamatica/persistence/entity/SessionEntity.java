package com.viamatica.viamatica.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSesion")
    private Integer id;

    @Column(name = "fechaIngreso")
    private LocalDateTime loginDate;

    @Column(name = "fechaSalida")
    private LocalDateTime logoutDate;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private UserEntity user;
}
