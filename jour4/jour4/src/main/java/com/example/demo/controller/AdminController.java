package com.example.demo.controller;

import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for administration features.
 * Only accessible by users with ADMIN role.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;

    @Autowired
    public AdminController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Display the admin dashboard.
     */
    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("personCount", personService.findAllPersons().size());
        return "admin/dashboard";
    }
}