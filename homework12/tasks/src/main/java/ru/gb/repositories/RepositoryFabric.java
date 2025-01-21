package ru.gb.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RepositoryFabric {

    @Autowired
    private Map<String, TaskRepoInterface> repositories;

    public TaskRepoInterface getRepository(String method) {
        return repositories.get(method);
    }
}
