package exercise.controller;

import exercise.repository.PersonRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    @GetMapping
    public List<Person> showAll() {
        return personRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.status(201).body(person);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Person> createPerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
