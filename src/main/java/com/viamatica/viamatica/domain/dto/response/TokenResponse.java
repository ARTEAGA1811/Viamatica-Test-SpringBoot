package com.viamatica.viamatica.domain.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TokenResponse {
    private String token;
    private String message;
    private String username;
}
