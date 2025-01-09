package ru.gb.services;

import org.springframework.stereotype.Service;
import ru.gb.annotations.MeasureTime;
import ru.gb.models.Employee;
import ru.gb.repositories.EmployeeRepository;

import java.util.List;

@Service
public class ApiService {

    private final EmployeeRepository repository;

    public ApiService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @MeasureTime
    public List<Employee> getAllEmploys() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    public boolean deleteEmployeeById(Long id) {
        Employee empl = getEmployeeById(id);
        if (empl != null) {
            repository.delete(empl);
            return true;
        }
        return false;
    }
}
