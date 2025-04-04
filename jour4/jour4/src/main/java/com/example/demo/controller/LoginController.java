package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for authentication-related pages.
 */
@Controller
public class LoginController {

    /**
     * Display the login page.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Redirect to the persons page when accessing the root URL.
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/persons";
    }
}