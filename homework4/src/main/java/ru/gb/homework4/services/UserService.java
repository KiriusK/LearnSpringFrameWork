package ru.gb.homework4.services;

import org.springframework.stereotype.Service;
import ru.gb.homework4.models.User;
import ru.gb.homework4.repositories.RepositoryList;

import java.util.List;

@Service
public class UserService {

    RepositoryList repo;

    public UserService(RepositoryList repo) {
        this.repo = repo;
    }

    public List<User> getAllUser() {
        return repo.getAllUser();
    }

    public void addUser (User user) {
        repo.addUser(user);
    }
}
