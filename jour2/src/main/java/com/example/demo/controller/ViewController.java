
package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/view2")
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
        
        return "view2"; // It will return view2.html
    }
}