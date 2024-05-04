package com.viamatica.viamatica.business.port;

import com.viamatica.viamatica.domain.dto.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface ISessionService extends IEntityCrudService<Session, Long> {
    List<LocalDateTime> getLoginDatesByUserId(Long userId);

    List<LocalDateTime> getLogoutDatesByUserId(Long userId);
}
