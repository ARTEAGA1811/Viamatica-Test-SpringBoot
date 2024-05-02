package com.viamatica.viamatica.persistence.mapper;

import com.viamatica.viamatica.domain.dto.User;
import com.viamatica.viamatica.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserPersistenceMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "sessionActive", source = "sessionActive"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "roles", source = "roles"),
            @Mapping(target = "person", source = "person"),
            @Mapping(target = "sessions", source = "sessions"),
    })
    User toUser(UserEntity userEntity);

    List<User> toUsers(List<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}
