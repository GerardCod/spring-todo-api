package com.gerardcod.todosapi.controllers;

import com.gerardcod.todosapi.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<User> createAccount(User user);
    ResponseEntity<User> updateAccount(User user, Long id);
    ResponseEntity<List<User>> getAllAccounts();
    ResponseEntity<User> getAccountById(Long id);
    ResponseEntity<User> deleteAccunt(Long id);
}
