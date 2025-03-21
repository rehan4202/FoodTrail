package com.localfoodfinder.user_service.service;

import com.localfoodfinder.user_service.model.User;
import com.localfoodfinder.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        // Encrypt password before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    // Login user (verify credentials)
    public Optional<User> loginUser(User user) {
        // Check if the user object and password are not null
        if (user == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }

        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        // Check if user exists and validate password
        if (existingUser.isPresent()) {
            String storedPasswordHash = existingUser.get().getPassword();

            // Check if stored password hash is not null before comparing
            if (storedPasswordHash != null && BCrypt.checkpw(user.getPassword(), storedPasswordHash)) {
                return existingUser;
            }
        }

        return Optional.empty();  // Return empty if user not found or passwords don't match
    }

    // Get user profile by ID
    public Optional<User> getUserProfile(String userId) {
        return userRepository.findById(userId);
    }

    // Update user preferences
    public User updateUserPreferences(String userId, User user) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setPreferences(user.getPreferences());
            updatedUser.setFavoriteRestaurants(user.getFavoriteRestaurants());
            return userRepository.save(updatedUser);
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }
}
