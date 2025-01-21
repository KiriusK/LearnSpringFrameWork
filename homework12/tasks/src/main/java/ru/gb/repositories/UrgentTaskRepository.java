package ru.gb.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.models.UrgentTask;

@Repository("urgentTask")
public interface UrgentTaskRepository extends ListCrudRepository<UrgentTask, Long>, TaskRepoInterface {
}