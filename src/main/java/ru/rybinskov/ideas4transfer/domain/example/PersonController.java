package ru.rybinskov.ideas4transfer.domain.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> findAll() {
        return repository.findAll();
    }

    @PostMapping("/persons")
    public void createDelivery(@RequestBody Person person) throws JsonProcessingException {

        repository.save(person);
    }


}
