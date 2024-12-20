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
import ru.gb.homework5.models.Project;
import ru.gb.homework5.repositories.ProjectRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectResource {

    private final ProjectRepository projectRepository;

    private final ObjectMapper objectMapper;

    public ProjectResource(ProjectRepository projectRepository, ObjectMapper objectMapper) {
        this.projectRepository = projectRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public PagedModel<Project> getAll(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return new PagedModel<>(projects);
    }

    @GetMapping("/{id}")
    public Project getOne(@PathVariable Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        System.out.println(projectOptional);
        return projectOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }


    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PatchMapping("/{id}")
    public Project patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        Project project = projectRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(project).readValue(patchNode);

        return projectRepository.save(project);
    }


    @DeleteMapping("/{id}")
    public Project delete(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            projectRepository.delete(project);
        }
        return project;
    }

}
