package com.viamatica.viamatica.business.service;

import com.viamatica.viamatica.business.port.ISessionService;
import com.viamatica.viamatica.domain.dto.Session;
import com.viamatica.viamatica.domain.repository.ISessionRepository;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionServiceImpl implements ISessionService {
    @Autowired
    private ISessionRepository sessionRepository;

    @Override
    public List<Session> getAll() {
        return sessionRepository.getAll();
    }

    @Override
    public Session getById(Long aLong) {
        return sessionRepository.getById(aLong).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.SESSION_NOT_FOUND));
    }

    @Override
    public Session create(Session entity) {
        return sessionRepository.create(entity);
    }

    @Override
    public Session update(Long aLong, Session entity) {
        return sessionRepository.getById(aLong)
                .map(s -> {
                    s.setLoginDate(entity.getLoginDate());
                    s.setLogoutDate(entity.getLogoutDate());
                    return sessionRepository.update(s);
                }).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.SESSION_NOT_FOUND));
    }

    @Override
    public void delete(Long aLong) {
        sessionRepository.delete(aLong);
    }

    @Override
    public List<LocalDateTime> getLoginDatesByUserId(Long userId) {
        return sessionRepository.getLoginDatesByUserId(userId);
    }

    @Override
    public List<LocalDateTime> getLogoutDatesByUserId(Long userId) {
        return sessionRepository.getLogoutDatesByUserId(userId);
    }
}
