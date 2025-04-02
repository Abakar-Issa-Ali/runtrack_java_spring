package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class ViewController {

    @GetMapping("/view4")
    public String view(Model model) {
        model.addAttribute("message", "Hello from Thymeleaf!");
        
        // Create a list of products
        List<Product> products = Arrays.asList(
            new Product(1L, "Ordinateur portable", 999.99),
            new Product(2L, "Smartphone", 699.99),
            new Product(3L, "Tablette", 349.99),
            new Product(4L, "Casque audio", 129.99),
            new Product(5L, "Enceinte Bluetooth", 79.99)
        );
        
        // Add a message to the model
        model.addAttribute("products", products);
        
        // Add an attribute for the user
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        
        // Add an attribute for the welcome message
        if (!model.containsAttribute("welcomeMessage")) {
            model.addAttribute("welcomeMessage", "");
        }
        
        return "view4";
    }
    
@PostMapping("/submitForm")
public String submitForm(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
    System.out.println("Form submitted with user: " + user.getName() + ", " + user.getEmail() + ", " + user.getAge());
    
    // Verify form validation
    if (bindingResult.hasErrors()) {
        System.out.println("Validation errors: " + bindingResult.getAllErrors());
        
        // Readd the list of products
        List<Product> products = Arrays.asList(
            new Product(1L, "Ordinateur portable", 999.99),
            new Product(2L, "Smartphone", 699.99),
            new Product(3L, "Tablette", 349.99),
            new Product(4L, "Casque audio", 129.99),
            new Product(5L, "Enceinte Bluetooth", 79.99)
        );
        model.addAttribute("products", products);
        
        // The user is automatically added to the model
        return "view4";
    }
    
    // If no errors, display a welcome message
    String welcomeMessage = "Welcome, " + user.getName() + " ! We have received your information : " 
        + user.getEmail() + ", " + user.getAge() + " years old.";
    
    System.out.println("Welcome message: " + welcomeMessage);
    
    // Add the welcome message
    model.addAttribute("welcomeMessage", welcomeMessage);
    
    // Readd the list of products
    List<Product> products = Arrays.asList(
        new Product(1L, "Ordinateur portable", 999.99),
        new Product(2L, "Smartphone", 699.99),
        new Product(3L, "Tablette", 349.99),
        new Product(4L, "Casque audio", 129.99),
        new Product(5L, "Enceinte Bluetooth", 79.99)
    );
    model.addAttribute("products", products);
    
    // Reinitialization of the user object after successful form submission
    model.addAttribute("user", new User());
    
    return "view4";
 }
}