package com.viamatica.viamatica.domain.repository;

import com.viamatica.viamatica.domain.dto.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface ISessionRepository extends IEntityCrudRepository<Session, Long> {
    List<LocalDateTime> getLoginDatesByUserId(Long userId);
    List<LocalDateTime> getLogoutDatesByUserId(Long userId);
}
