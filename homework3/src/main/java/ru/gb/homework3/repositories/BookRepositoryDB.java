package ru.gb.homework3.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.gb.homework3.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile(value = "DB")
public class BookRepositoryDB implements BookRepository{

    private int idGen;
    private final String url = "jdbc:h2:mem:main;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:database/schema.sql'";
    private final Connection con;

    public BookRepositoryDB() throws SQLException {
        this.con = DriverManager.getConnection(url);
        idGen = 1;
    }

    @Override
    public void addBook(Book book) {
        book.setId(idGen++);
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO `books` (`id`,`name`,`author`) VALUES (?,?,?);");
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error in add: " + ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try {
            Statement state = con.createStatement();
            ResultSet set = state.executeQuery("SELECT * FROM `books`;");
            while(set.next()) {
                Book book = new Book(set.getString(2), set.getString(3));
                book.setId(set.getInt(1));
                bookList.add(book);
            }
        } catch (SQLException ex) {
            System.out.println("Error in get: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
        return bookList;
    }

    @Override
    public void deleteBookByName(String name) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM `books` WHERE name = ?;");
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Error in delete: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
