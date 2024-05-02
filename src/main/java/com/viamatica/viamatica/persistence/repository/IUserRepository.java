package com.viamatica.viamatica.persistence.repository;

import com.viamatica.viamatica.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
}
