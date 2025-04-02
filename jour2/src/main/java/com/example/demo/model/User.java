// package com.example.demo.model;

// public class User {
//     private String name;
//     private String email;

//     // Constructor
//     public User() {
//     }

//     public User(String name, String email) {
//         this.name = name;
//         this.email = email;
//     }

//     // Getters and Setters
//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }
// }

package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class User {
    @NotBlank(message = "The name is required")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters")
    private String name;
    
    @NotBlank(message = "The email is required")
    @Email(message = "The email is not valid")
    private String email;
    
    @NotNull(message = "The age is required")
    @Min(value = 18, message = "The age must be at least 18")
    private Integer age;

    public User() {
    }

    public User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
}