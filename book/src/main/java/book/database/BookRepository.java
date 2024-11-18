package book.database;

import book.addbook.AddBookDBBoundary;
import book.entities.Book;

    public class BookRepository {
    private AddBookDBBoundary database;

    public BookRepository(AddBookDBBoundary database) {
        this.database = database;
    }

    public void addBook(Book book) {
        database.saveBook(book);
    }
}
