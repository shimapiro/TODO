package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Todo;
import com.example.demo.entity.Todo.Priority;
import com.example.demo.service.TodoService;

@Controller
public class TodoViewController {

	private final TodoService todoService;

	public TodoViewController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/")
	public String viewTodos(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String sortBy,
			Model model) {
		
		List<Todo> tasks;

		if (keyword != null && !keyword.isEmpty()) {
			tasks = todoService.searchTodos(keyword);
		} else if (sortBy != null && !sortBy.isEmpty()) {
			tasks = todoService.getsorTodos(sortBy);
		} else {
			tasks = todoService.getAllTodos();
		}
		model.addAttribute("tasks", tasks);
		return "todo-list";
	}

	@GetMapping("/create")
	public String createTodoForm() {
		return "todo-create";
	}

	@PostMapping("/create")
	public String createTodo(@RequestParam String title,
			@RequestParam String description,
			@RequestParam String category,
			@RequestParam String priority,
			@RequestParam(required = false) String dueDate) {
		Todo todo = new Todo();
		todo.setTitle(title);
		todo.setDescription(description);
		todo.setCategory(category);
		todo.setPriority(Priority.valueOf(priority));

		if (dueDate != null && !dueDate.isEmpty()) {
			todo.setDueDate(LocalDate.parse(dueDate));
		}
		todoService.createOrUpdateTodo(todo);

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Integer id) {
		todoService.deleteTodoById(id);
		return "redirect:/";
	}

//	@GetMapping("/toggle/{id}")
//	public String toggleTaskStautus(@PathVariable Integer id) {
//		Todo todo = todoService.getTodoById(id).orElse(null);
//		if (todo != null) {
//			todo.setCompleted(!todo.isCompleted());
//			todoService.createOrUpdateTodo(todo);
//		}
//		return "redirect:/";
//	}

	@GetMapping("/details/{id}")
	public String viewTaskDetails(@PathVariable Integer id, Model model) {
		Todo todo = todoService.getTodoById(id).orElse(null);
		if (todo != null) {
			model.addAttribute("task", todo);
			return "todo-details";
		}
		return "redirect:/";
	}
	
	
	@GetMapping("/complete/{id}")
	public String completeAndDeleteTask(@PathVariable Integer id) {
		todoService.deleteTodoById(id);
		return "redirect:/";
	}
	

}
