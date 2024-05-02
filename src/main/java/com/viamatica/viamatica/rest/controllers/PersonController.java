package com.viamatica.viamatica.rest.controllers;

import com.viamatica.viamatica.business.port.IPersonService;
import com.viamatica.viamatica.domain.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping("")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> persons = personService.getAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getById(id);
        return ResponseEntity.ok(person);
    }

    @PostMapping("")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person newPerson = personService.create(person);
        return ResponseEntity.ok(newPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personService.update(id, person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok("Person deleted");
    }
}
