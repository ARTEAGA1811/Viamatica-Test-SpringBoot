package com.viamatica.viamatica.domain.dto;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Session {
    private Long id;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;

    private User user;



}
