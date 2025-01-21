package ru.gb.listeners;

import org.springframework.context.ApplicationListener;
import ru.gb.events.DeleteEmployeeEvent;
import ru.gb.models.Employee;

public class DeleteEmployeeListener implements ApplicationListener<DeleteEmployeeEvent> {

    @Override
    public void onApplicationEvent(DeleteEmployeeEvent event) {
        Employee employee = event.getEmployee();
        System.out.println("Сотрудник был удален: " + employee.getName());
    }
}
