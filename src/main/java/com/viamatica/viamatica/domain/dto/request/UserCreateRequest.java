package com.viamatica.viamatica.domain.dto.request;

import com.viamatica.viamatica.domain.dto.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "The username is required")
    @Size(min = 8, max = 20, message = "The username must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{8,20}$", message = "The username must have at least one number and one uppercase letter")
    private String username;

    @NotBlank(message = "The password is required")
    @Size(min = 8, message = "The password must be at least 8 characters")
    //Agrega la validación que el password debe tener al menos una letra mayúscula, no debe tener espacios y debe tener al menos un signo
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9]).{8,}$", message = "The password must have at least one uppercase letter, one lowercase letter, one number, one special character and no spaces")
    private String password;

    @NotBlank(message = "The personId is required")
    private Integer personId;
    private List<String> roles;

    public UserCreateRequest() {
        this.roles = new ArrayList<>();
    }
}
