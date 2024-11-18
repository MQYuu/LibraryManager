package library.database;

import java.util.List;

import library.bookdata.Book;

public interface BookDatabaseBoundary {
    List<Book> getAllBooks();
    
    Book getBookById(String bookId);
    
    void addBook(Book book);
    
    void updateBook(Book book);
    
    void removeBook(String bookId);
}

