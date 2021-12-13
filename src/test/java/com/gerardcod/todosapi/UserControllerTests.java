package com.gerardcod.todosapi;

import com.gerardcod.todosapi.controllers.UserContollerImpl;
import com.gerardcod.todosapi.entities.User;
import com.gerardcod.todosapi.repositories.UserRepository;
import com.gerardcod.todosapi.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTests {
    @InjectMocks
    private UserContollerImpl controller;

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    private User user;
    private User userResult;

    @Before
    public void init() {
        System.out.println("Before Tests");
        service = new UserServiceImpl(repository);
        controller = new UserContollerImpl(service);
        user = new User();
        user.setName("Gerardo");
        user.setLastName("Aguilar");
        user.setEmail("gerardo.aguilar.calderon@gmail.com");
        user.setPassword("Qwerty1234");
        userResult = new User(1L, "Gerardo", "Aguilar", "gerardo.aguilar.calderon@gmail.com", "Qwerty1234", null );
    }

    private final List<User> userListMock = new ArrayList<>() {
        private static final long serialVersionUID = 3364623274644575756L;
        {
            add(new User());
            add(new User(1L, "Gerardo", null, null, null, null));
        }
    };

    @Test
    public void shouldHaveSameUsers() {
        when(repository.findAll()).thenReturn(userListMock);
        ResponseEntity<List<User>> response = controller.getAllAccounts();
        assertEquals(userListMock, response.getBody());
        assertEquals(userListMock.size(), response.getBody().size());
    }

    @Test
    public void shouldReturnSameUser() {
        when(repository.save(user)).thenReturn(userResult);
        ResponseEntity<User> response = controller.createAccount(user);
        assertEquals(userResult, response.getBody());
    }

    @Test
    public void shouldReturnSameUsers() {
        when(repository.findById(1L)).thenReturn(Optional.of(userResult));
        ResponseEntity<User> response = controller.getAccountById(1L);
        assertEquals(userResult, response.getBody());
    }

    @Test
    public void shoulReturnTheSameUserAfterModification() {
        when(repository.save(user)).thenReturn(userResult);
        ResponseEntity<User> response = controller.updateAccount(user, 1L);
        assertEquals(userResult, response.getBody());
    }

    @Test
    public void shouldDeleteUser() {
        User userExample = new User();
        userExample.setId(1L);
        ResponseEntity<User> response = controller.deleteAccunt(1L);
        Mockito.verify(repository, times(1)).deleteById(1L);
        Assert.assertEquals(userExample, response.getBody());
    }
}
