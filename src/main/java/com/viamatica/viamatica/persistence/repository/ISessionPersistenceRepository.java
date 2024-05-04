package com.viamatica.viamatica.persistence.repository;

import com.viamatica.viamatica.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISessionPersistenceRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findByUserIdOrderByLoginDate(Long userId);

    List<SessionEntity> findByUserIdOrderByLogoutDate(Long userId);
}
