package usecase;
import java.util.List;

import bookdata.Book;

public class CalculateTotalPriceUseCase {
    private List<Book> books;

    public CalculateTotalPriceUseCase(List<Book> books) {
        this.books = books;
    }

    public double execute(Class<? extends Book> bookType) {
        double total = 0;
        for (Book book : books) {
            if (bookType.isInstance(book)) {
                total += book.getTotalPrice();
            }
        }
        return total;
    }
}
