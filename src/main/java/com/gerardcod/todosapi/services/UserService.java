package com.gerardcod.todosapi.services;

import com.gerardcod.todosapi.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> createAccount(User user);
    Optional<User> updateProfile(User user, Long id);
    Optional<List<User>> getAllAccounts();
    Optional<User> getUserById(Long id);
    Optional<User> deleteAccount(Long id);
}
