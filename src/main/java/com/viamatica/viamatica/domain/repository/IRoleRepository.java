package com.viamatica.viamatica.domain.repository;

import com.viamatica.viamatica.domain.dto.Role;

import java.util.Optional;

public interface IRoleRepository extends IEntityCrudRepository<Role, Long> {
    Optional<Role> getRoleByName(String name);
}
