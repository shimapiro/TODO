package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;

// コントローラーであることをSpringに教えるアノテーション
@RestController
@RequestMapping("/api/todos") // このクラスは /api/todos に関連付けられる
public class TodoController {

    // サービスクラスを利用する
    private final TodoService todoService;

    // コンストラクタでサービスを注入（Springが自動的に提供してくれる）
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // (1) 全てのタスクを取得するエンドポイント
    @GetMapping
    public List<Todo> getTodoAll(){
    	return todoService.getAllTodos();
    }
    
    
    // (2) IDで特定のタスクを取得するエンドポイント
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok) // タスクが見つかれば 200 OK を返す
                   .orElse(ResponseEntity.notFound().build()); // 見つからなければ 404 Not Found を返す
    }
    

    // (3) 新しいタスクを作成するエンドポイント
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createOrUpdateTodo(todo); // 新しいタスクを作成
        return ResponseEntity.ok(createdTodo); // 作成されたタスクを返す
    }

    
    // (4) タスクを更新するエンドポイント
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        todo.setId(id); // URLで指定されたIDをタスクに設定
        Todo updatedTodo = todoService.createOrUpdateTodo(todo); // タスクを更新
        return ResponseEntity.ok(updatedTodo); // 更新されたタスクを返す
    }

    
    
    // (5) タスクを削除するエンドポイント
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Integer id) {
        todoService.deleteTodoById(id); // サービスでタスクを削除
        return ResponseEntity.noContent().build(); // 204 No Content を返す
    }
}
