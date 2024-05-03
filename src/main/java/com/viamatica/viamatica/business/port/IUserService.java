package com.viamatica.viamatica.business.port;

import com.viamatica.viamatica.domain.dto.User;

import java.util.List;

public interface IUserService extends IEntityCrudService<User, Long> {
    User getByUsername(String username);
    void addAttempt(String username);
}
