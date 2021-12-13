package com.gerardcod.todosapi.services;

import com.gerardcod.todosapi.entities.User;
import com.gerardcod.todosapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Optional<User> createAccount(User user) {
        User userCreated = repository.save(user);
        return Optional.of(userCreated);
    }

    @Override
    public Optional<User> updateProfile(User user, Long id) {
        user.setId(id);
        User userResult = repository.save(user);
        return Optional.of(userResult);
    }

    @Override
    public Optional<List<User>> getAllAccounts() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return Optional.of(users);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> deleteAccount(Long id) {
        repository.deleteById(id);
        User user = new User();
        user.setId(id);
        return Optional.of(user);
    }
}
