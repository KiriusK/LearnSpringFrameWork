package ru.gb.homework4.models;



public class User {

    private String login;
    private String name;
    private int age;


    public User(String login, String name, int age) {
        this.login = login;
        this.name = name;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

