package com.talvix.userservice.service;

import com.talvix.userservice.dto.AuthResponse;
import com.talvix.userservice.dto.RegisterRequest;
import com.talvix.userservice.entity.User;
import com.talvix.userservice.entity.UserRole;
import com.talvix.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String USER_CREATED_TOPIC = "user.created";

    public AuthResponse register(RegisterRequest request) {

        log.info("Registering user: {}", request.getEmail());

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already registered " +request.getEmail());
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail((request.getEmail()));
        user.setHeadline(request.getHeadline());
        user.setLocation(request.getLocation());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.NORMAL_USER);

        User savedUser = userRepository.save(user);
        log.info("User Registered: {}", savedUser.getId());

        // Publish user.created event
        // Search service consume this and indexes user

        Map<String, Object> userCreatedEvent = new HashMap<>();
        userCreatedEvent.put("userId", savedUser.getId());
        userCreatedEvent.put("firstName", savedUser.getFirstName());
        userCreatedEvent.put("lastName", savedUser.getLastName());
        userCreatedEvent.put("email", savedUser.getEmail());
        userCreatedEvent.put("headline", savedUser.getHeadline());
        userCreatedEvent.put("location", savedUser.getLocation());

        kafkaTemplate.send(USER_CREATED_TOPIC, savedUser.getId(), userCreatedEvent);

        log.info("user.created event published: {}", savedUser.getId());

        String token = generateToken(savedUser.getId(), savedUser.getEmail());

        return buildAuthResponse(savedUser, token);
    }

    private AuthResponse buildAuthResponse(User user, String token) {

        AuthResponse response = new AuthResponse();
        response.setAccessToken(token);
        response.setRefreshToken(generateRefreshToken(user.getId()));
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());


    }

}
