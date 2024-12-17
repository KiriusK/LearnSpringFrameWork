package ru.gb.homework3.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.gb.homework3.models.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile(value = "List")
@Primary
public class BookRepositoryList implements BookRepository {

    private int idGen;
    private final List<Book> booksStorage;

    public BookRepositoryList() {
        idGen = 1;
        this.booksStorage = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        book.setId(idGen++);
        booksStorage.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return booksStorage;
    }

    @Override
    public void deleteBookByName(String name) {
        Book book = booksStorage.stream().filter(e -> e.getName().equalsIgnoreCase(name)).toList().get(0);
        if (book != null) {
            booksStorage.remove(book);
        }
    }
}
