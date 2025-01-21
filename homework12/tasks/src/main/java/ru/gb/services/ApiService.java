package ru.gb.services;

import org.springframework.stereotype.Service;
import ru.gb.annotations.MeasureTime;
import ru.gb.models.Task;
import ru.gb.repositories.RepositoryFabric;
import ru.gb.repositories.TaskRepository;

import java.util.List;

@Service
public class ApiService {

    private final TaskRepository repository;

    public ApiService() {
        this.repository = (TaskRepository) new RepositoryFabric().getRepository("task");
    }


    @MeasureTime
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public boolean deleteTaskById(Long id) {
        Task task = getTaskById(id);
        if (task != null) {
            repository.delete(task);
            return true;
        }
        return false;
    }
}
