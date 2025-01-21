package ru.gb.events;

import org.springframework.context.ApplicationEvent;
import ru.gb.models.Employee;

public class AddEmployeeEvent extends ApplicationEvent {
    private final Employee employee;

    public AddEmployeeEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
