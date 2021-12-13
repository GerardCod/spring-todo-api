package com.gerardcod.todosapi.controllers;

import com.gerardcod.todosapi.entities.User;
import com.gerardcod.todosapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserContollerImpl implements UserController {
    private final UserService service;

    @Autowired
    public UserContollerImpl(UserService service) {
        super();
        this.service = service;
    }

    @Override
    @PostMapping("/accounts")
    public ResponseEntity<User> createAccount(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(user).get());
    }

    @Override
    @PutMapping("/accounts/{id}")
    public ResponseEntity<User> updateAccount(@RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateProfile(user, id).get());
    }

    @Override
    @GetMapping("/accounts")
    public ResponseEntity<List<User>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts().get());
    }

    @Override
    @GetMapping("/accounts/{id}")
    public ResponseEntity<User> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id).get());
    }

    @Override
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<User> deleteAccunt(@PathVariable Long id) {
        Optional<User> ref = service.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.OK).body(ref.get());
    }
}
