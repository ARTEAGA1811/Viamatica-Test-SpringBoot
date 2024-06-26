package com.viamatica.viamatica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    private Long id;
    private String name;
    private boolean isActive;
}
