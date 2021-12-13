package com.gerardcod.todosapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerardcod.todosapi.entities.Todo;
import com.gerardcod.todosapi.repositories.TodosRepository;

@Service
public class TodosServiceImpl implements TodosService {
	private TodosRepository repository;

	@Autowired
	public TodosServiceImpl(TodosRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Optional<Boolean> save(Todo todo) {
		// TODO Auto-generated method stub
		Boolean isDone = false;
		try {
			repository.save(todo);
			isDone = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return Optional.of(isDone);
	}

	@Override
	public Optional<Boolean> update(Todo todo) {
		// TODO Auto-generated method stub
		Boolean isDone = false;
		try {
			repository.save(todo);
			isDone = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return Optional.of(isDone);
	}

	@Override
	public Optional<List<Todo>> findAll() {
		// TODO Auto-generated method stub
		List<Todo> listTodo = new ArrayList<>();
		try {
			repository.findAll().forEach(listTodo::add);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return Optional.of(listTodo);
	}

	@Override
	public Optional<Todo> findById(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Optional<Boolean> deleteById(Long id) {
		// TODO Auto-generated method stub
		Boolean isDone = false;
		try {
			repository.deleteById(id);
			isDone = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return Optional.of(isDone);
	}

}
