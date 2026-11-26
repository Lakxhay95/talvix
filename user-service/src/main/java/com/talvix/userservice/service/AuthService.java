package com.talvix.userservice.service;

import com.talvix.userservice.dto.AuthResponse;
import com.talvix.userservice.dto.RegisterRequest;
import com.talvix.userservice.entity.User;
import com.talvix.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
        user.setLocation(request.setLocation());
    }

}
