package com.viamatica.viamatica.domain.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean sessionActive;
    private String status;

    private Person person;

    private List<Role> roles;

    private List<Session> sessions;
}
