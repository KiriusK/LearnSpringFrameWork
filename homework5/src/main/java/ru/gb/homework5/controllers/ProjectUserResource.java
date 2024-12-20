package ru.gb.homework5.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.homework5.models.ProjectUser;
import ru.gb.homework5.repositories.UserRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class ProjectUserResource {

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    public ProjectUserResource(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public PagedModel<ProjectUser> getAll(Pageable pageable) {
        Page<ProjectUser> projectUsers = userRepository.findAll(pageable);
        return new PagedModel<>(projectUsers);
    }

    @GetMapping("/{id}")
    public ProjectUser getOne(@PathVariable Long id) {
        Optional<ProjectUser> projectUserOptional = userRepository.findById(id);
        return projectUserOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }


    @PostMapping
    public ProjectUser create(@RequestBody ProjectUser projectUser) {
        return userRepository.save(projectUser);
    }

    @PatchMapping("/{id}")
    public ProjectUser patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        ProjectUser projectUser = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(projectUser).readValue(patchNode);

        return userRepository.save(projectUser);
    }


    @DeleteMapping("/{id}")
    public ProjectUser delete(@PathVariable Long id) {
        ProjectUser projectUser = userRepository.findById(id).orElse(null);
        if (projectUser != null) {
            userRepository.delete(projectUser);
        }
        return projectUser;
    }

}
