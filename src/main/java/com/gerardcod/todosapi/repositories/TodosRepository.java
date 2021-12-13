package com.gerardcod.todosapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gerardcod.todosapi.entities.Todo;

@Repository
public interface TodosRepository extends CrudRepository<Todo, Long> {

}
