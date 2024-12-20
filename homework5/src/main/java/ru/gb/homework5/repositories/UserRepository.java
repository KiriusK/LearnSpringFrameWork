package ru.gb.homework5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.homework5.models.ProjectUser;

@Repository
public interface UserRepository extends JpaRepository<ProjectUser, Long> {
}
