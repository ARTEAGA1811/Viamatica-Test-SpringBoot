package com.viamatica.viamatica.business.service;

import com.viamatica.viamatica.business.port.IRoleService;
import com.viamatica.viamatica.domain.dto.Role;
import com.viamatica.viamatica.domain.repository.IRoleRepository;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<Role> getAll() {
        return roleRepository.getAll();
    }

    @Override
    public Role getById(Long aLong) {
        return roleRepository.getById(aLong).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.ROLE_NOT_FOUND));
    }

    @Override
    public Role create(Role entity) {
        return roleRepository.create(entity);
    }

    @Override
    public Role update(Long aLong, Role entity) {
        return roleRepository.getById(aLong)
                .map(r -> {
                    r.setName(entity.getName());
                    return roleRepository.update(r);
                }).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.ROLE_NOT_FOUND));
    }

    @Override
    public void delete(Long aLong) {
        roleRepository.delete(aLong);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.ROLE_NOT_FOUND));
    }
}
