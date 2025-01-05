package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> getAllTodos(){
		return todoRepository.findAll();
	}	
	
	public Optional<Todo> getTodoById(Integer id) {
		return todoRepository.findById(id);
	}
	
	public Todo createOrUpdateTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public void deleteTodoById(Integer id) {
		todoRepository.deleteById(id);
	}
}
