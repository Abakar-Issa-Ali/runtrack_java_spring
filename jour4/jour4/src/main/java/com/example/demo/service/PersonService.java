package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Find all persons in the database.
     *
     * @return List of all persons
     */
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Find a person by their ID.
     *
     * @param id The ID of the person to find
     * @return Optional containing the found person or empty if not found
     */
    public Optional<Person> findPersonById(Long id) {
        return personRepository.findById(id);
    }

    /**
     * Save a new person or update an existing one.
     *
     * @param person The person to save or update
     * @return The saved person with generated ID (for new entities)
     */
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Delete a person by their ID.
     *
     * @param id The ID of the person to delete
     */
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    /**
     * Initialize the database with sample data.
     * This is used when the application starts to ensure some data exists.
     */
    public void initializeData() {
        if (personRepository.count() == 0) {
            personRepository.save(new Person("Jean Dupont", 30));
            personRepository.save(new Person("Marie Martin", 25));
            personRepository.save(new Person("Pierre Durand", 40));
        }
    }
}