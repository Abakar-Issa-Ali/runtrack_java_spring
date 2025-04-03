package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        
        //  Some initial data for demonstration
        if (personRepository.count() == 0) {
            personRepository.save(new Person("Ali Abakar", 25));
            personRepository.save(new Person("Issa Adam", 27));
            personRepository.save(new Person("Moussa Mht", 40));
        }
    }

    // Display the list of all persons.
    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("newPerson", new Person());
        return "persons/list3";
    }

    
    // Handle the form submission to create a new person.
    @PostMapping
    public String createPerson(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/persons";
    }
    

    // Display the form to edit a person.
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person ID: " + id));
        model.addAttribute("person", person);
        return "persons/edit";
    }
    
    
    // Handle the form submission to update a person.
    
    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable Long id, @ModelAttribute Person person) {
        person.setId(id);
        personRepository.save(person); // save() will update if the entity has an ID
        return "redirect:/persons";
    }
    
    // Delete a person.

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return "redirect:/persons";
    }
}