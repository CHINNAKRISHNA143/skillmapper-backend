package com.mapper.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mapper.model.User;
import com.mapper.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 🔐 Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().trim().toLowerCase());


        return userRepository.save(user);
    }
    
    public Map<String, Object> login(Map<String, String> payload) {

        Map<String, Object> response = new HashMap<>();

        String email = payload.get("email");
        String password = payload.get("password");

        if (email == null || password == null) {
            response.put("message", "Email and password are required");
            response.put("success", false);
            return response;
        }

        // 🔥 Normalize email
        email = email.trim().toLowerCase();

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            response.put("message", "Invalid email or password");
            response.put("success", false);
            return response;
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            response.put("message", "Invalid email or password");
            response.put("success", false);
            return response;
        }

        // ✅ SUCCESS RESPONSE
        response.put("success", true);
        response.put("message", "Login successful");
        response.put("email", user.getEmail());
        response.put("fullName", user.getFullName());
        response.put("profileImage", user.getProfileImage()); // may be null

        return response;
    }


}

