package com.viamatica.viamatica.persistence.repository;

import com.viamatica.viamatica.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, Long> {
}
