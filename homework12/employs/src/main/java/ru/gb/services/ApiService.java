package ru.gb.services;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.gb.annotations.MeasureTime;
import ru.gb.events.AddEmployeeEvent;
import ru.gb.events.DeleteEmployeeEvent;
import ru.gb.models.Employee;
import ru.gb.repositories.EmployeeRepository;

import java.util.List;

@Service
public class ApiService {

    private final EmployeeRepository repository;
    private final ApplicationEventPublisher publisher;

    public ApiService(EmployeeRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @MeasureTime
    public List<Employee> getAllEmploys() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addEmployee(Employee employee) {
        publisher.publishEvent(new AddEmployeeEvent(this, employee));
        repository.save(employee);
    }

    public boolean deleteEmployeeById(Long id) {
        Employee empl = getEmployeeById(id);
        if (empl != null) {
            repository.delete(empl);
            publisher.publishEvent(new DeleteEmployeeEvent(this, empl));
            return true;
        }
        return false;
    }
}
