import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gb.models.Task;
import ru.gb.repositories.TaskRepository;
import ru.gb.services.ApiService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class ApiServiceUnitTests {

    @InjectMocks
    private ApiService service;

    @Mock
    private TaskRepository repository;

    @BeforeAll
    public void setupBefore() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmploys() {
        Task task = new Task();
        task.setName("test task");
        task.setDescription("test task descript");
        List<Task> assertEmpl = Collections.singletonList(task);
        Mockito.when(repository.findAll()).thenReturn(assertEmpl);

        List<Task> resultTask = service.getAllTasks();

        Assert.assertEquals(assertEmpl, resultTask);
    }

    @Test
    public void testGetEmployeeById() {
        Task task = new Task();
        task.setName("test task");
        task.setDescription("test task descript");
        long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(task));

        Task resultTask = service.getTaskById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Assert.assertEquals(task, resultTask);
    }

    @Test
    public void testGetEmployeeByErrorId() {
        long id = 1L;

        Task resultTask = service.getTaskById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Assert.assertNull(resultTask);
    }

    @Test
    public void testDeleteEmployeeById() {
        Task task = new Task();
        task.setName("test task");
        task.setDescription("test task descript");
        long id = 1L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(task));

        boolean result = service.deleteTaskById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(repository, Mockito.times(1)).delete(task);
        Assert.assertTrue(result);
    }

    @Test
    public void testDeleteEmployeeByErrorId() {
        long id = 1L;

        boolean result = service.deleteTaskById(id);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(repository);
        Assert.assertFalse(result);
    }

    @Test
    public void testAddEmployee() {
        Task task = new Task();
        task.setName("test task");
        task.setDescription("test task descript");

        service.addTask(task);

        Mockito.verify(repository, Mockito.times(1)).save(task);
    }
}
