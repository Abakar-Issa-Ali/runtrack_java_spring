package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new user with encoded password.
     * 
     * @param user the user to register
     * @return the saved user
     */
    @Transactional
    public User registerNewUser(User user) {
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Ensure new users have ROLE_USER
        user.addRole("ROLE_USER");
        
        // Save the user
        return userRepository.save(user);
    }
@PostConstruct
public void init() {
    // Créer un admin si aucun n'existe
    if (!isUsernameTaken("admin")) {
        User admin = new User("admin", "admin@example.com", passwordEncoder.encode("admin"));
        admin.addRole("ROLE_USER");
        admin.addRole("ROLE_ADMIN");
        userRepository.save(admin);
    }
}
    /**
     * Find a user by username.
     * 
     * @param username the username to search for
     * @return an Optional containing the user if found
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Check if a username is already taken.
     * 
     * @param username the username to check
     * @return true if the username is taken, false otherwise
     */
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Check if an email is already in use.
     * 
     * @param email the email to check
     * @return true if the email is in use, false otherwise
     */
    public boolean isEmailInUse(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Get all users.
     * 
     * @return a list of all users
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Make a user an admin by adding the ROLE_ADMIN role.
     * 
     * @param userId the ID of the user to make an admin
     * @return the updated user
     */
    @Transactional
    public Optional<User> makeUserAdmin(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.addRole("ROLE_ADMIN");
            return Optional.of(userRepository.save(user));
        }
        
        return Optional.empty();
    }
}