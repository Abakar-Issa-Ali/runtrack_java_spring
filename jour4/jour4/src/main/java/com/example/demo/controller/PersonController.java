package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
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

    private final PersonService personService;

    /**
     * Constructor-based dependency injection of PersonService.
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
        // Initialize sample data
        personService.initializeData();
    }

    /**
     * Display the list of all persons.
     */
    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.findAllPersons());
        model.addAttribute("newPerson", new Person());
        return "persons/list";
    }

    /**
     * Handle the form submission to create a new person.
     */
    @PostMapping
    public String createPerson(@ModelAttribute Person person) {
        personService.savePerson(person);
        return "redirect:/persons";
    }
    
    /**
     * Display the form to edit a person.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Person person = personService.findPersonById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person ID: " + id));
        model.addAttribute("person", person);
        return "persons/edit";
    }
    
    /**
     * Handle the form submission to update a person.
     */
    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable Long id, @ModelAttribute Person person) {
        person.setId(id); // Ensure the ID is set correctly
        personService.savePerson(person); // save() will update if the entity has an ID
        return "redirect:/persons";
    }
    
    /**
     * Delete a person.
     */
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return "redirect:/persons";
    }
}