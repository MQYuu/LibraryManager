package book.addbook;

import book.entities.Book;

public interface AddBookDBBoundary {
    void saveBook(Book book);
}
