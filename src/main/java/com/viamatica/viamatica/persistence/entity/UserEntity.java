package com.viamatica.viamatica.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @NotBlank
    @Size(min = 8, max = 20, message = "The username must be between 8 and 20 characters")
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "The password must be at least 8 characters")
    @NotBlank
    private String password;

    @Column(name = "mail", nullable = false, unique = true, length = 120)
    @Email
    @NotBlank
    @Size(max = 120, message = "The email must be less than 120 characters")
    private String email;

    @Column(name = "sessionActive")
    private boolean sessionActive;

    @Column(name = "status")
    private String status;

    @ManyToOne(targetEntity = PersonEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona")
    private PersonEntity person;

    @ManyToMany(targetEntity = RoleEntity.class, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idRol"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SessionEntity> sessions;

    @PrePersist
    protected void onCreate() {
        this.status = "active";
    }


}
