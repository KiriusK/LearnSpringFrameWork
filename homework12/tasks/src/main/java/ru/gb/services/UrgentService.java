package ru.gb.services;

import ru.gb.annotations.MeasureTime;
import ru.gb.models.UrgentTask;
import ru.gb.repositories.RepositoryFabric;
import ru.gb.repositories.UrgentTaskRepository;

import java.util.List;

public class UrgentService {

    private final UrgentTaskRepository repository;

    public UrgentService() {
        this.repository = (UrgentTaskRepository) new RepositoryFabric().getRepository("urgentTask");
    }


    @MeasureTime
    public List<UrgentTask> getAllTasks() {
        return repository.findAll();
    }

    public UrgentTask getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void addTask(UrgentTask task) {
        repository.save(task);
    }

    public boolean deleteTaskById(Long id) {
        UrgentTask task = getTaskById(id);
        if (task != null) {
            repository.delete(task);
            return true;
        }
        return false;
    }
}
