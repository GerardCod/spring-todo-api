package com.gerardcod.todosapi;

import com.gerardcod.todosapi.controllers.TodoController;
import com.gerardcod.todosapi.entities.Todo;
import com.gerardcod.todosapi.entities.User;
import com.gerardcod.todosapi.repositories.TodosRepository;
import com.gerardcod.todosapi.services.TodosServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(value = MockitoJUnitRunner.class)
public class TodosControllerTests {

    @InjectMocks
    private TodoController controller;

    @InjectMocks
    private TodosServiceImpl service;

    @Mock
    private TodosRepository repository;

    private Todo todoToCreate;
    private Todo todoResult;
    private User userAux;

    @Before
    public void init() {
        service = new TodosServiceImpl(repository);
        controller = new TodoController(service);
        userAux = new User();
        userAux.setId(1L);
        todoToCreate = new Todo();
        todoToCreate.setTitle("Finish the unit tests");
        todoToCreate.setDescription("I gotta finish the unit tests with junit today");
        todoToCreate.setStartDate(Date.from(Instant.parse("2021-07-12T00:00:00.00Z")));
        todoToCreate.setEndDate(Date.from(Instant.parse("2021-07-12T00:00:00.00Z")));
        todoToCreate.setUser(userAux);
        todoResult = new Todo(1L, "Finish the unit tests", "I gotta finish the unit tests with junit today", false, Date.from(Instant.parse("2021-07-12T00:00:00.00Z")), Date.from(Instant.parse("2021-07-12T00:00:00.00Z")), Date.from(Instant.now()), userAux);
    }

    private List<Todo> todoListMock = new ArrayList<>() {
      private static final long serialVersionUID = 2L;
      {
          add(new Todo());
          add(new Todo());
      }
    };

    @Test
    public void shouldReturnSameCollection() {
        Mockito.when(repository.findAll()).thenReturn(todoListMock);
        ResponseEntity<List<Todo>> response = controller.findAll();
        Assert.assertEquals(todoListMock, response.getBody());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createShoulReturnTrue() {
        Mockito.when(repository.save(todoToCreate)).thenReturn(todoResult);
        ResponseEntity<Boolean> response = controller.save(todoToCreate, 1L);
        Assert.assertTrue(response.getBody());
    }

    @Test
    public void shouldReturnSameTodo() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(todoResult));
        ResponseEntity<Todo> response = controller.findById(1L);
        Assert.assertEquals(todoResult, response.getBody());
    }

    @Test
    public void updateShouldReturnTrue() {
        Mockito.when(repository.save(todoResult)).thenReturn(todoResult);
        ResponseEntity<Boolean> response = controller.update(1L, todoResult);
        Assert.assertTrue(response.getBody());
    }

    @Test
    public void deleteShouldReturnTrue() {
        ResponseEntity<Boolean> response = controller.delete(1L);
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
        Assert.assertTrue(response.getBody());
    }
}
