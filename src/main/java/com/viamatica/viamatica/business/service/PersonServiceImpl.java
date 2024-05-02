package com.viamatica.viamatica.business.service;

import com.viamatica.viamatica.business.port.IPersonService;
import com.viamatica.viamatica.domain.dto.Person;
import com.viamatica.viamatica.domain.repository.IPersonRepository;
import com.viamatica.viamatica.errors.EntityNotFoundException;
import com.viamatica.viamatica.utils.ErrorCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return personRepository.getAll();
    }

    @Override
    public Person getById(Long aLong) {
        return personRepository.getById(aLong).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.PERSON_NOT_FOUND));
    }

    @Override
    public Person create(Person entity) {
        return personRepository.create(entity);
    }

    @Override
    public Person update(Long aLong, Person entity) {
        return personRepository.getById(aLong)
                .map(p -> {
                    p.setNames(entity.getNames());
                    p.setLastNames(entity.getLastNames());
                    p.setIdentification(entity.getIdentification());
                    p.setBirthDate(entity.getBirthDate());
                    return personRepository.update(p);
                }).orElseThrow(() -> new EntityNotFoundException(ErrorCatalog.PERSON_NOT_FOUND));
    }

    @Override
    public void delete(Long aLong) {
        personRepository.delete(aLong);
    }
}
