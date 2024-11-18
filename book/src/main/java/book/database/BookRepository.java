package book.database;

import java.util.List;

import book.entities.Book;

    public class BookRepository {
    private BookDBBoundary database;

    public BookRepository(BookDBBoundary database) {
        this.database = database;
    }

    public void addBook(Book book) {
        database.saveBook(book);
    }

    public List<Book> getAllBooks() {
        return database.getAllBooks();
    }
}
