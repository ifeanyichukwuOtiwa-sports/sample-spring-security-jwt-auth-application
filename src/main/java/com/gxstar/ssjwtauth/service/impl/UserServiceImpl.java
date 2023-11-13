package com.gxstar.ssjwtauth.service.impl;

import com.gxstar.ssjwtauth.model.User;
import com.gxstar.ssjwtauth.repository.UserRepository;
import com.gxstar.ssjwtauth.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final User saved = userRepository.save(user);
        saved.setPassword("********");
        return saved;
    }

    @Override
    public List<User> getUsers() {
        final List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> new User(UUID.randomUUID(), u.getName(), u.getUsername(), u.getEmail(), "*********"))
                .toList();
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }
}
