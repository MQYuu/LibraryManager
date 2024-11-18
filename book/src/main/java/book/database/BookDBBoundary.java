package book.database;

import java.util.List;

import book.entities.Book;

public interface BookDBBoundary {
    void saveBook(Book book);
    List<Book> getAllBooks();
}
