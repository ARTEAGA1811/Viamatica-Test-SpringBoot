package com.viamatica.viamatica.persistence.mapper;

import com.viamatica.viamatica.domain.dto.Person;
import com.viamatica.viamatica.persistence.entity.PersonEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPersonPersistenceMapper {
    Person toPerson(PersonEntity personEntity);

    List<Person> toPersons(List<PersonEntity> personEntities);

    @InheritInverseConfiguration
    PersonEntity toPersonEntity(Person person);

}
