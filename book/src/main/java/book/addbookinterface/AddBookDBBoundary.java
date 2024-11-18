package book.addbookinterface;

import book.entities.AddBook;

public interface AddBookDBBoundary {
    void saveBook(AddBook book);
}
