package ru.gb.events;

import org.springframework.context.ApplicationEvent;
import ru.gb.models.Employee;

public class DeleteEmployeeEvent extends ApplicationEvent {
    private final Employee employee;

    public DeleteEmployeeEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
