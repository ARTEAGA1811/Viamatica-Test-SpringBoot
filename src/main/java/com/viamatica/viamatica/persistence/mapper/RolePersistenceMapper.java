package com.viamatica.viamatica.persistence.mapper;

import com.viamatica.viamatica.domain.dto.Role;
import com.viamatica.viamatica.persistence.entity.RoleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {
    Role toRole(RoleEntity roleEntity);

    List<Role> toRoles(List<RoleEntity> roleEntities);

    @InheritInverseConfiguration
    RoleEntity toRoleEntity(Role role);
}
