package com.viamatica.viamatica.persistence.adapter;

import com.viamatica.viamatica.domain.dto.Role;
import com.viamatica.viamatica.domain.repository.IRoleRepository;
import com.viamatica.viamatica.persistence.mapper.RolePersistenceMapper;
import com.viamatica.viamatica.persistence.repository.IRolePersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RolePersistenceAdapter implements IRoleRepository {
    @Autowired
    private IRolePersistenceRepository rolePersistenceRepository;
    @Autowired
    private RolePersistenceMapper roleMapper;

    @Override
    public List<Role> getAll() {
        return roleMapper.toRoles(this.rolePersistenceRepository.findAll());
    }

    @Override
    public Optional<Role> getById(Long aLong) {
        return rolePersistenceRepository.findById(aLong).map(roleMapper::toRole);
    }

    @Override
    public Role create(Role entity) {
        return roleMapper.toRole(rolePersistenceRepository.save(roleMapper.toRoleEntity(entity)));
    }

    @Override
    public Role update(Role entity) {
        return roleMapper.toRole(rolePersistenceRepository.save(roleMapper.toRoleEntity(entity)));
    }

    @Override
    public void delete(Long aLong) {
        rolePersistenceRepository.deleteById(aLong);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return rolePersistenceRepository.findByName(name).map(roleMapper::toRole);
    }
}
