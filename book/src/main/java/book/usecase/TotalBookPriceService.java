package book.usecase;


import book.database.BookRepository;
import book.entities.Book;
import book.totalbookprice.TotalBookPriceInputBoundary;
import book.totalbookprice.TotalBookPriceOutputBoundary;
import book.totalbookprice.TotalBookPriceResponseData;

import java.util.List;

public class TotalBookPriceService implements TotalBookPriceInputBoundary {
    private BookRepository bookRepository;
    private TotalBookPriceOutputBoundary outputBoundary;

    public TotalBookPriceService(BookRepository bookRepository, TotalBookPriceOutputBoundary outputBoundary) {
        this.bookRepository = bookRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void calculateTotalBookPrice() {
        List<Book> books = bookRepository.getAllBooks();
        double totalPrice = 0;

        for (Book book : books) {
            totalPrice += book.calculateTotalPrice();
        }

        TotalBookPriceResponseData responseData = new TotalBookPriceResponseData(totalPrice);
        outputBoundary.presentTotalBookPrice(responseData);
    }
}

