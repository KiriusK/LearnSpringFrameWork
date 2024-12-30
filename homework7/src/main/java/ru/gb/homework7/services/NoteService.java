package ru.gb.homework7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.homework7.auxiliaries.NoteStatus;
import ru.gb.homework7.models.Note;
import ru.gb.homework7.models.Status;
import ru.gb.homework7.repositories.NoteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repo;

    public List<Note> getAllNotes() {
        return repo.findAll();
    }

    public Note getNoteById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void createNote(Note note) {
        note.setCreateDate(LocalDate.now());
        note.setStatus(NoteStatus.CREATED);
        repo.save(note);
    }

    public Note changeNote(Long id, Note note) {
        Note updNote = repo.findById(id).orElse(null);
        if (updNote!=null) {
            updNote.setHeader(note.getHeader());
            updNote.setContent(note.getContent());
            return repo.save(updNote);
        } else {
            return null;
        }
    }

    public void deleteNote(Long id) {
        repo.findById(id).ifPresent(repo::delete);
    }

    public Note changeStatus(Long id, Status status) {
        Note updNote = repo.findById(id).orElse(null);
        if (updNote!=null) {
            updNote.setStatus(status.getStatus());
            return repo.save(updNote);
        } else {
            return null;
        }
    }

}
