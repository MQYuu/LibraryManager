package book.usecase;

import book.averageunitprice.AverageUnitPriceInputBoundary;
import book.averageunitprice.AverageUnitPriceOutputBoundary;
import book.averageunitprice.AverageUnitPriceResponseData;
import book.database.BookRepository;
import book.entities.ReferenceBook;

import book.entities.Book;

public class AverageUnitPriceService implements AverageUnitPriceInputBoundary {
    private BookRepository repository;
    private AverageUnitPriceOutputBoundary outputBoundary;

    public AverageUnitPriceService(BookRepository repository, AverageUnitPriceOutputBoundary outputBoundary) {
        this.repository = repository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void calculateAverageUnitPrice() {
        double totalUnitPrice = 0;
        int count = 0;

        // Sử dụng kiểu Book thay vì var
        for (Book book : repository.getAllBooks()) {
            if (book instanceof ReferenceBook) {
                totalUnitPrice += book.getUnitPrice();
                count++;
            }
        }

        double averageUnitPrice = count > 0 ? totalUnitPrice / count : 0;
        AverageUnitPriceResponseData responseData = new AverageUnitPriceResponseData(averageUnitPrice);
        outputBoundary.presentAverageUnitPriceResult(responseData);
    }
}


