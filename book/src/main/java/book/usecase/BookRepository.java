package book.usecase;

import book.addbookinterface.AddBookDBBoundary;
import book.entities.AddBook;

    public class BookRepository {
    private AddBookDBBoundary database;

    public BookRepository(AddBookDBBoundary database) {
        this.database = database;
    }

    public void addBook(AddBook book) {
        database.saveBook(book);
    }
}
