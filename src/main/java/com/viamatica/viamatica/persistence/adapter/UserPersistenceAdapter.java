package com.viamatica.viamatica.persistence.adapter;

import com.viamatica.viamatica.domain.dto.User;
import com.viamatica.viamatica.domain.repository.IUserRepository;
import com.viamatica.viamatica.persistence.mapper.IUserPersistenceMapper;
import com.viamatica.viamatica.persistence.repository.IUserPersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceAdapter implements IUserRepository {
    @Autowired
    private IUserPersistenceRepository userPersistenceRepository;
    @Autowired
    private IUserPersistenceMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.toUsers(this.userPersistenceRepository.findAll());
    }

    @Override
    public Optional<User> getById(Long id) {
        return userPersistenceRepository.findById(id).map(userMapper::toUser);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userPersistenceRepository.findByUsername(username).map(userMapper::toUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userPersistenceRepository.existsByEmail(email);
    }

    @Override
    public User create(User user) {
        return userMapper.toUser(userPersistenceRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public User update(User user) {
        return userMapper.toUser(userPersistenceRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public void delete(Long id) {
        userPersistenceRepository.deleteById(id);
    }

}
