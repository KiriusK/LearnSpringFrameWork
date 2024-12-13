package ru.gb.homework3.controllers;

import org.springframework.web.bind.annotation.*;
import ru.gb.homework3.models.Book;
import ru.gb.homework3.services.BookService;

import java.util.List;

@RestController()
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBook(@RequestBody Book book) {
        service.addBook(book);
        return "Ok";
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable(value = "name") String name) {
        service.deleteBookByName(name);
        return "Ok";
    }

    @ExceptionHandler(Exception.class)
    public String allExceptionHandler(Exception ex) {
        return ex.getMessage();
    }


}
