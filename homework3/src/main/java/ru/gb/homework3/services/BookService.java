package ru.gb.homework3.services;

import org.springframework.stereotype.Service;
import ru.gb.homework3.models.Book;
import ru.gb.homework3.repositories.BookRepository;
import java.util.List;

@Service
public class BookService {

    final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public void addBook(Book book) {
        repository.addBook(book);
    }

    public void deleteBookByName(String name) {
        repository.deleteBookByName(name);
    }
}
