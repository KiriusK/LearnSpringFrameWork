package ru.gb.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.annotations.ConsoleLogging;
import ru.gb.models.Task;
import ru.gb.services.ApiService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class ApiController {

    private final ApiService service;

    public ApiController(ApiService service) {
        this.service = service;
    }

    @ConsoleLogging
    @GetMapping("/all")
    public ResponseEntity<List<Task>> allTasks() {
        return new ResponseEntity<>(service.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task resTask = service.getTaskById(id);
        if (resTask != null) {
            return new ResponseEntity<>(resTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(resTask, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        if (service.deleteTaskById(id)) {
            return new ResponseEntity<>("Удалено успешно", HttpStatus.OK);
        }
        return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addTask(@RequestBody Task task) {
        service.addTask(task);
        return new ResponseEntity<>("Успешно", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> errHandler(Exception ex) {
        System.out.println("Произошла ошибка: " + ex.getMessage());
        return new ResponseEntity<>("Тут ошибка", HttpStatus.NOT_FOUND);
    }

}
