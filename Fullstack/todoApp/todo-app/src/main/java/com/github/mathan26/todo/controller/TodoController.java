package com.github.mathan26.todo.controller;


import com.github.mathan26.todo.model.Todo;
import com.github.mathan26.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(id,todo));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteById(@PathVariable Long id) {
        todoService.deleteTodoByid(id);
        return ResponseEntity.noContent().build();
    }

}
