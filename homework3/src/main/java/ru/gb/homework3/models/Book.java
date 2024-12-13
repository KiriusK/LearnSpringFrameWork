package ru.gb.homework3.models;


public class Book {

    private int id;
    private String name;
    private String author;
    private static int idGen=1;

//    public Book() {}

    public Book(String name, String author) {
        id = idGen++;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
