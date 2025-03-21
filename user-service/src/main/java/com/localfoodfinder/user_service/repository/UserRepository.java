package com.localfoodfinder.user_service.repository;

import com.localfoodfinder.user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find user by username (needed for authentication)
    Optional<User> findByUsername(String username);
}
