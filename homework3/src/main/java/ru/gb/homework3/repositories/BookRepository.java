package ru.gb.homework3.repositories;

import ru.gb.homework3.models.Book;

import java.util.List;

public interface BookRepository {

    void addBook(Book book);
    List<Book> getAllBooks();
    void deleteBookByName(String name);


}
