package ru.gb.homework6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.homework6.models.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}