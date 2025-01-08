package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer>{
		List<Todo> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title,String description);
		List<Todo> findAllByOrderByDueDateAsc();
		List<Todo> findAllByOrderByPriorityDesc();
}
