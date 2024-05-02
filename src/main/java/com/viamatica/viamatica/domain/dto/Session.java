package com.viamatica.viamatica.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    private Long id;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;

    private User user;



}
