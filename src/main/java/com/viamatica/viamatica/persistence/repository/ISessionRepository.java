package com.viamatica.viamatica.persistence.repository;

import com.viamatica.viamatica.persistence.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionRepository extends JpaRepository<SessionEntity, Long> {
}
