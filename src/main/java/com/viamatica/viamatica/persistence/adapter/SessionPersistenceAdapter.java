package com.viamatica.viamatica.persistence.adapter;

import com.viamatica.viamatica.domain.dto.Session;
import com.viamatica.viamatica.domain.repository.ISessionRepository;
import com.viamatica.viamatica.persistence.mapper.ISessionPersistenceMapper;
import com.viamatica.viamatica.persistence.repository.ISessionPersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SessionPersistenceAdapter implements ISessionRepository {
    @Autowired
    private ISessionPersistenceRepository sessionPersistenceRepository;
    @Autowired
    private ISessionPersistenceMapper sessionMapper;

    @Override
    public List<Session> getAll() {
        return sessionMapper.toSessions(this.sessionPersistenceRepository.findAll());
    }

    @Override
    public Optional<Session> getById(Long aLong) {
        return sessionPersistenceRepository.findById(aLong).map(sessionMapper::toSession);
    }

    @Override
    public Session create(Session entity) {
        return sessionMapper.toSession(sessionPersistenceRepository.save(sessionMapper.toSessionEntity(entity)));
    }

    @Override
    public Session update(Session entity) {
        return sessionMapper.toSession(sessionPersistenceRepository.save(sessionMapper.toSessionEntity(entity)));
    }

    @Override
    public void delete(Long aLong) {
        sessionPersistenceRepository.deleteById(aLong);
    }
}
