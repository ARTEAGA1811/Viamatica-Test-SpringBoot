package com.viamatica.viamatica.persistence.repository;

import com.viamatica.viamatica.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
