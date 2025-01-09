package ru.gb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.annotations.ConsoleLogging;
import ru.gb.models.Employee;
import ru.gb.services.ApiService;

import java.util.List;

@RestController
@RequestMapping("/employs")
public class ApiController {

    private final ApiService service;

    public ApiController(ApiService service) {
        this.service = service;
    }

    @ConsoleLogging
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> allEmploys() {
        return new ResponseEntity<>(service.getAllEmploys(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee resEmpl = service.getEmployeeById(id);
        if (resEmpl != null) {
            return new ResponseEntity<>(resEmpl, HttpStatus.OK);
        }
        return new ResponseEntity<>(resEmpl, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (service.deleteEmployeeById(id)) {
            return new ResponseEntity<>("Удалено успешно", HttpStatus.OK);
        }
        return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        service.addEmployee(employee);
        return new ResponseEntity<>("Успешно", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> errHandler(Exception ex) {
        System.out.println("Произошла ошибка: " + ex.getMessage());
        return new ResponseEntity<>("Тут ошибка", HttpStatus.NOT_FOUND);
    }

}
