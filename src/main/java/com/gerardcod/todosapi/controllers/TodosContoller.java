package com.gerardcod.todosapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gerardcod.todosapi.entities.Todo;

public interface TodosContoller {
	ResponseEntity<List<Todo>> findAll();
	ResponseEntity<Boolean> save(Todo todo, Long idUser);
	ResponseEntity<Boolean> update(Long id, Todo todo);
	ResponseEntity<Boolean> delete(Long id);
	ResponseEntity<Todo> findById(Long id);
}
