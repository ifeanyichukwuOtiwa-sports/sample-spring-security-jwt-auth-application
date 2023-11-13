package com.gxstar.ssjwtauth.service.api;

import com.gxstar.ssjwtauth.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    List<User> getUsers();
    Optional<User> findByUsername(String username);
}
