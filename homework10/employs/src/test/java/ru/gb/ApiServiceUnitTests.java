package ru.gb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import ru.gb.models.Employee;
import ru.gb.repositories.EmployeeRepository;
import ru.gb.services.ApiService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class ApiServiceUnitTests {

    @InjectMocks
    private ApiService service;

    @Mock
    private EmployeeRepository repository;

    @BeforeAll
    public void setupBefore() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmploys() {
        Employee employee = new Employee();
        employee.setName("testEmpl");
        employee.setSalary(10000);
        List<Employee> assertEmpl = Collections.singletonList(employee);
        Mockito.when(repository.findAll()).thenReturn(assertEmpl);

        List<Employee> resultEmpl = service.getAllEmploys();

        Assert.assertEquals(assertEmpl, resultEmpl);
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee();
        employee.setName("testEmpl");
        employee.setSalary(10000);
        long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(employee));

        Employee resultEmpl = service.getEmployeeById(1L);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Assert.assertEquals(employee, resultEmpl);
    }

    @Test
    public void testGetEmployeeByErrorId() {
        long id = 1L;

        Employee resultEmpl = service.getEmployeeById(1L);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Assert.assertNull(resultEmpl);
    }

    @Test
    public void testDeleteEmployeeById() {
        Employee employee = new Employee();
        employee.setName("testEmpl");
        employee.setSalary(10000);
        long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(employee));

        boolean result = service.deleteEmployeeById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(repository, Mockito.times(1)).delete(employee);
        Assert.assertTrue(result);
    }

    @Test
    public void testDeleteEmployeeByErrorId() {
        long id = 1L;

        boolean result = service.deleteEmployeeById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(repository);
        Assert.assertFalse(result);
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee();
        employee.setName("testEmpl");
        employee.setSalary(10000);

        service.addEmployee(employee);

        Mockito.verify(repository, Mockito.times(1)).save(employee);
    }
}
