package com.viamatica.viamatica.business.service;

import com.viamatica.viamatica.business.port.IUserService;
import com.viamatica.viamatica.domain.dto.User;
import com.viamatica.viamatica.domain.repository.IUserRepository;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.USER_NOT_FOUND));
    }

    @Override
    public User create(User entity) {
        //TODO: ADD PASSWORD ENCODING
        return userRepository.create(entity);
    }

    @Override
    public User update(Long id, User entity) {
        return userRepository.getById(id)
                .map(u -> {
                    u.setUsername(entity.getUsername());
                    u.setPassword(entity.getPassword());
                    u.setEmail(entity.getEmail());
                    u.setRoles(entity.getRoles());
                    return userRepository.update(u);
                }).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.USER_NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
