package com.viamatica.viamatica.domain.repository;

import com.viamatica.viamatica.domain.dto.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IEntityCrudRepository<User, Long>{


    Optional<User> getUserByUsername(String name);

}
