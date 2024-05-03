package com.viamatica.viamatica.domain.repository;

import com.viamatica.viamatica.domain.dto.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IEntityCrudRepository<User, Long>{

    Optional<User> getUserByUsername(String name);
    boolean existsByEmail(String email);
    Optional<User> getUserByEmail(String email);
    void addAttempt(String username);
}
