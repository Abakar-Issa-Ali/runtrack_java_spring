package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Person entity.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

     // Find persons by their name.
    
    List<Person> findByName(String name);

    // Find persons with age greater than the specified value.

    List<Person> findByAgeGreaterThan(Integer age);

    // Find persons with name containing the specified string (case insensitive).
    
    List<Person> findByNameContainingIgnoreCase(String namePart);

    // Find persons by name and age.

    List<Person> findByNameAndAge(String name, Integer age);

    // Find persons by name ordered by age in ascending order.

    List<Person> findByNameOrderByAgeAsc(String name);
}