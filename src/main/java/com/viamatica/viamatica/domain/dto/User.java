package com.viamatica.viamatica.domain.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private Integer failedAttempts;

    private Person person;

    private Set<Role> roles;

//    private Set<Session> sessions;
}
