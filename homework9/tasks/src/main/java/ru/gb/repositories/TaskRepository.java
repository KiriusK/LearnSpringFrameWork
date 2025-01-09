package ru.gb.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.models.Task;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Long> {
}