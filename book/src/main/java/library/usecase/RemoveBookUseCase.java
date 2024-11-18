package library.usecase;
import java.util.List;

import library.bookdata.Book;

public class RemoveBookUseCase {
    private List<Book> books;

    public RemoveBookUseCase(List<Book> books) {
        this.books = books;
    }

    public boolean execute(String bookId) {
        return books.removeIf(book -> book.getBookId().equals(bookId));
    }
}
