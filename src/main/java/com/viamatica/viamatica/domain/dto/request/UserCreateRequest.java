package com.viamatica.viamatica.domain.dto.request;

import com.viamatica.viamatica.domain.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateRequest {
    private String username;
    private String password;
    private Integer personId;
    private List<String> roles;

    public UserCreateRequest() {
        this.roles = new ArrayList<>();
    }
}
