package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for user registration.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Display the registration form.
     */
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Process the registration form submission.
     */
    @PostMapping
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        // Check if username is already taken
        if (userService.isUsernameTaken(user.getUsername())) {
            redirectAttributes.addFlashAttribute("errorMessage", "This username is already taken");
            return "redirect:/register";
        }
        
        // Check if email is already in use
        if (userService.isEmailInUse(user.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "This email is already in use");
            return "redirect:/register";
        }
        
        // Register the new user
        userService.registerNewUser(user);
        
        redirectAttributes.addFlashAttribute("successMessage", 
                "Registration successful! You can now log in.");
        return "redirect:/login";
    }
}