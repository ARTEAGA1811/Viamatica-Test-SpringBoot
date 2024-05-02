package com.viamatica.viamatica.domain.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonRequest {
    private String names;
    private String lastNames;
    private String identification;
    private String birthDate;
}
