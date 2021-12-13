package com.gerardcod.todosapi.controllers;

import java.util.List;

import com.gerardcod.todosapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gerardcod.todosapi.entities.Todo;
import com.gerardcod.todosapi.services.TodosService;

@RestController
public class TodoController implements TodosContoller  {
	private TodosService service;

	@Autowired
	public TodoController(TodosService service) {
		this.service = service;
	}

	@GetMapping("/annotations")
	@Override
	public ResponseEntity<List<Todo>> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.findAll().get());
	}

	@Override
	@PostMapping("/annotations/{idUser}")
	public ResponseEntity<Boolean> save(@RequestBody Todo todo, @PathVariable Long idUser) {
		User userRelated = new User();
		userRelated.setId(idUser);
		todo.setUser(userRelated);
		Boolean result = service.save(todo).get();
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/annotations/{id}")
	@Override
	public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody Todo todo) {
		// TODO Auto-generated method stub
		todo.setId(id);
		Boolean result = service.update(todo).get();
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/annotations/{id}")
	@Override
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		// TODO Auto-generated method stu
		Todo todo = new Todo();
		todo.setId(id);
		Boolean result = service.deleteById(1L).get();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/annotations/{id}")
	@Override
	public ResponseEntity<Todo> findById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		Todo todo = service.findById(id).get();
		return ResponseEntity.ok(todo);
	}
	
	
}
