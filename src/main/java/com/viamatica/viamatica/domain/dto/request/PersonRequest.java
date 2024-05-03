package com.viamatica.viamatica.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonRequest {
    @NotBlank(message = "Names are required")
    @Size(max = 60, message = "Names must be less than 60 characters")
    private String names;

    @NotBlank(message = "Last names are required")
    @Size(max = 60, message = "Last names must be less than 60 characters")
    private String lastNames;

    @NotBlank(message = "Identification is required")
    @Size(min = 10, max = 10, message = "Identification must be 10 characters")
    @Pattern(regexp = "^[0-9]*$", message = "The identification must be only numbers")
    private String identification;

    private String birthDate;
}
