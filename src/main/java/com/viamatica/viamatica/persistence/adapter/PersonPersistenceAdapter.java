package com.viamatica.viamatica.persistence.adapter;

import com.viamatica.viamatica.domain.dto.Person;
import com.viamatica.viamatica.domain.repository.IPersonRepository;
import com.viamatica.viamatica.persistence.mapper.IPersonPersistenceMapper;
import com.viamatica.viamatica.persistence.repository.IPersonPersistenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonPersistenceAdapter implements IPersonRepository {
    @Autowired
    private IPersonPersistenceRepository personRepository;
    @Autowired
    private IPersonPersistenceMapper personMapper;

    @Override
    public List<Person> getAll() {
        return personMapper.toPersons(this.personRepository.findAll());
    }

    @Override
    public Optional<Person> getById(Long aLong) {
        return personRepository.findById(aLong).map(personMapper::toPerson);
    }

    @Override
    public Person create(Person entity) {
        return personMapper.toPerson(personRepository.save(personMapper.toPersonEntity(entity)));
    }

    @Override
    public Person update(Person entity) {
        return personMapper.toPerson(personRepository.save(personMapper.toPersonEntity(entity)));
    }

    @Override
    public void delete(Long aLong) {
        personRepository.deleteById(aLong);
    }
}
