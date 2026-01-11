package com.mapper.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

 

import com.mapper.model.User;
import com.mapper.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(
    	    value = "/register",
    	    consumes = {
    	        MediaType.MULTIPART_FORM_DATA_VALUE,
    	        MediaType.APPLICATION_JSON_VALUE
    	    }
    	)
    	public ResponseEntity<?> registerUser(
    	        @RequestParam String name,
    	        @RequestParam String email,
    	        @RequestParam String password,
    	        @RequestParam(required = false) MultipartFile image
    	) throws IOException {

    	    User user = new User();
    	    user.setFullName(name);
    	    user.setEmail(email);
    	    user.setPassword(password); // service will encode it

    	    // OPTIONAL image
    	    if (image != null && !image.isEmpty()) {
    	        String base64Image = Base64.getEncoder()
    	                .encodeToString(image.getBytes());
    	        user.setProfileImage(base64Image);
    	    }

    	    User savedUser = userService.register(user);

    	    return ResponseEntity.ok(savedUser);
    	}

    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> payload) {
        return userService.login(payload);
    }
}
