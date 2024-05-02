package com.viamatica.viamatica.persistence.mapper;

import com.viamatica.viamatica.domain.dto.Session;
import com.viamatica.viamatica.persistence.entity.SessionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISessionPersistenceMapper {
    Session toSession(SessionEntity sessionEntity);

    List<Session> toSessions(List<SessionEntity> sessionEntities);

    @InheritInverseConfiguration
    SessionEntity toSessionEntity(Session session);
}
