package ru.gb.homework8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.homework8.models.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}