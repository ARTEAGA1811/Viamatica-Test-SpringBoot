package com.viamatica.viamatica.persistence.repository;

import com.viamatica.viamatica.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserPersistenceRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

    //Agregar método para incrementar el número de intentos fallidos
//    @Modifying
//    @Transactional
    @Query(value = "update usuarios set intentos_fallidos = (intentos_fallidos + 1) where username = ?;", nativeQuery = true)
    void updateFailAttemps(String username);
}
