package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling Person-related web requests.
 * Uses Thymeleaf for server-side rendering.
 */
@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;


    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
        
        // Add some initial data for demonstration
        if (personRepository.count() == 0) {
            personRepository.save(new Person("Oussema Fatnassi", 29));
            personRepository.save(new Person("Abakar Issa", 27));
            personRepository.save(new Person("Baptiste Appriou", 24));
        }
    }
    
     // Display the list of all persons.
    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("newPerson", new Person());
        return "persons/list";
    }

     // Handle the form submission to create a new person.
    @PostMapping
    public String createPerson(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/persons";
    }
}