package com.viamatica.viamatica.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mail", nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "sessionActive")
    private boolean sessionActive;

    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = PersonEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona")
    private PersonEntity person;

    @ManyToMany(targetEntity = RoleEntity.class, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idRol"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<SessionEntity> sessions;


}
