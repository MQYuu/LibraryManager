package usecase;
import java.util.List;

import bookdata.Book;

public class AddBookUseCase {
    private List<Book> books;

    public AddBookUseCase(List<Book> books) {
        this.books = books;
    }

    public void execute(Book book) {
        books.add(book);
    }
}
