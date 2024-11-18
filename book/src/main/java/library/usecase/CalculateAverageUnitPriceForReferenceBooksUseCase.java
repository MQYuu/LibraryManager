package library.usecase;
import java.util.List;

import library.bookdata.Book;
import library.bookdata.ReferenceBook;

public class CalculateAverageUnitPriceForReferenceBooksUseCase {
    private List<Book> books;

    public CalculateAverageUnitPriceForReferenceBooksUseCase(List<Book> books) {
        this.books = books;
    }

    public double execute() {
        double totalUnitPrice = 0;
        int count = 0;
        for (Book book : books) {
            if (book instanceof ReferenceBook) {
                totalUnitPrice += book.getUnitPrice();
                count++;
            }
        }
        return count == 0 ? 0 : totalUnitPrice / count;
    }
}
