package com.viamatica.viamatica.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequest {
    @NotBlank(message = "The username is required")
    @Size(min = 8, max = 20, message = "The username must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{8,20}$", message = "The username must have at least one number and one uppercase letter")
    private String username;



    @NotBlank(message = "The personId is required")
    private Integer personId;

    private List<String> roles;

    public UserUpdateRequest() {
        this.roles = new ArrayList<>();
    }
}
