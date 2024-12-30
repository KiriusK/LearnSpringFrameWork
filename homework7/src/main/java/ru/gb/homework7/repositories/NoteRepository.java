package ru.gb.homework7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.homework7.models.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}