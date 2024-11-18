package usecase;
import java.util.List;

import bookdata.Book;

public class UpdateBookUseCase {
    private List<Book> books;

    public UpdateBookUseCase(List<Book> books) {
        this.books = books;
    }

    public void execute(String bookId, double newUnitPrice, int newQuantity) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                book.setUnitPrice(newUnitPrice);
                book.setQuantity(newQuantity);
                break;
            }
        }
    }
}
