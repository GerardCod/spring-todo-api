package com.gerardcod.todosapi.services;

import java.util.List;
import java.util.Optional;

import com.gerardcod.todosapi.entities.Todo;

public interface TodosService {
	Optional<Boolean> save(Todo todo);
	Optional<Boolean> update(Todo todo);
	Optional<List<Todo>> findAll();
	Optional<Todo> findById(Long id);
	Optional<Boolean> deleteById(Long id);
}
