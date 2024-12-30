package ru.gb.homework7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework7.models.Note;
import ru.gb.homework7.models.Status;
import ru.gb.homework7.models.UserRequest;
import ru.gb.homework7.services.JwtService;
import ru.gb.homework7.services.NoteService;

import java.util.List;


@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final JwtService jwtService;
    private final NoteService service;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return new ResponseEntity<>(service.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id) {
        Note note = service.getNoteById(id);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userRequest.getUsername(), userRequest.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        if (authenticationResponse.isAuthenticated()) {
            UserDetails principal = (UserDetails) authenticationResponse.getPrincipal();
            String token = jwtService.createNewToken(principal);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Note note) {
        service.createNote(note);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        Note updNote = service.changeNote(id, note);
        if (updNote != null) {
            return new ResponseEntity<>(updNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Note> changeStatus(@PathVariable Long id, @RequestBody Status status) {
        Note updNote = service.changeStatus(id, status);
        if (updNote != null) {
            return new ResponseEntity<>(updNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exHandler(Exception ex) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>("Произошла ошибка", HttpStatus.BAD_REQUEST);
    }


}
