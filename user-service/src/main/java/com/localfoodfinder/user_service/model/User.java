package com.localfoodfinder.user_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")  // The name of the collection in MongoDB
public class User {

    @Id
    private String id;  // MongoDB generates a unique ID for each document
    private String username;
    private String email;
    private String password;
    private List<String> favoriteRestaurants;  // List to store favorite restaurants
    private String profilePicture;  // Optional field to store profile picture URL or path
    private String preferences;  // Could be a JSON string or another object

    // Constructors
    public User() {
    }

    public User(String username, String email, String password, List<String> favoriteRestaurants, String profilePicture, String preferences) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteRestaurants = favoriteRestaurants;
        this.profilePicture = profilePicture;
        this.preferences = preferences;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFavoriteRestaurants() {
        return favoriteRestaurants;
    }

    public void setFavoriteRestaurants(List<String> favoriteRestaurants) {
        this.favoriteRestaurants = favoriteRestaurants;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", favoriteRestaurants=" + favoriteRestaurants +
                ", profilePicture='" + profilePicture + '\'' +
                ", preferences='" + preferences + '\'' +
                '}';
    }
}
