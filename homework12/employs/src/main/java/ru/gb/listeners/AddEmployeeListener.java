package ru.gb.listeners;

import org.springframework.context.ApplicationListener;
import ru.gb.events.AddEmployeeEvent;
import ru.gb.models.Employee;

public class AddEmployeeListener implements ApplicationListener<AddEmployeeEvent> {
    @Override
    public void onApplicationEvent(AddEmployeeEvent event) {
        Employee employee = event.getEmployee();
        System.out.println("Появился новый сотрудник: " + employee.getName());
    }
}
