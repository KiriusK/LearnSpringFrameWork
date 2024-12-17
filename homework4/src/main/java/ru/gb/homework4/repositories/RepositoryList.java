package ru.gb.homework4.repositories;

import org.springframework.stereotype.Repository;
import ru.gb.homework4.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryList {

    private final List<User> userList;

    public RepositoryList() {
        this.userList = new ArrayList<>();
        userList.add(new User("vvasia", "Василий", 26));
        userList.add(new User("lleo", "Леопольд", 22));
        userList.add(new User("alexxx", "Александер", 24));
    }

    public List<User> getAllUser() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }
}
