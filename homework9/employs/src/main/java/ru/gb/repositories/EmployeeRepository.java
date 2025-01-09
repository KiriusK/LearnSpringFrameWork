package ru.gb.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.models.Employee;

@Repository
public interface EmployeeRepository extends ListCrudRepository<Employee, Long> {
}