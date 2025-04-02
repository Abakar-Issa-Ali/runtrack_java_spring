package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;


// @Controller
// public class ViewController {

//     @GetMapping("/view")
//     public String view(Model model) {
//         model.addAttribute("message", "Hello from Thymeleaf!");
//         return "view"; // It will return view.html
//     }
// }


@Controller
public class ViewController {

   // @GetMapping("/view2")
    @GetMapping("/view3")
    public String view(Model model) {
        model.addAttribute("message", "Hello from Thymeleaf!");
        
        // Creation of a list of products
        List<Product> products = Arrays.asList(
            new Product(1L, "Ordinateur portable", 999.99),
            new Product(2L, "Smartphone", 699.99),
            new Product(3L, "Tablette", 349.99),
            new Product(4L, "Casque audio", 129.99),
            new Product(5L, "Enceinte Bluetooth", 79.99)
        );
        
        // Add the list of products to the model
        model.addAttribute("products", products);
        
        // Add an empty user to the model
        model.addAttribute("user", new User());
        
        // Add an empty welcome message
        model.addAttribute("welcomeMessage", "");
        
        return "view3"; // It will return view3.html
    }
    
    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute User user, Model model) {
        // Welcome message
        String welcomeMessage = "Welcome, " + user.getName() + " ! we have received your email : " + user.getEmail();
        
        // Add the welcome message to the model
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
        
        // Add a message
        model.addAttribute("message", "Hello from Thymeleaf!");
        
        // Reinitialize the user
        model.addAttribute("user", new User());
        
        return "view3";
    }
}