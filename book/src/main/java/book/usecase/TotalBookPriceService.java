package book.usecase;

import book.database.BookDBBoundary;
import book.entities.Book;
import book.totalbookprice.TotalBookPriceInputBoundary;
import book.totalbookprice.TotalBookPriceOutputBoundary;
import book.totalbookprice.TotalBookPriceResponseData;

import java.util.ArrayList;
import java.util.List;

public class TotalBookPriceService implements TotalBookPriceInputBoundary {
    private BookDBBoundary bookRepository;
    private TotalBookPriceOutputBoundary totalBookPriceOutputBoundary;

    public TotalBookPriceService(BookDBBoundary bookRepository,
            TotalBookPriceOutputBoundary totalBookPriceOutputBoundary) {
        this.bookRepository = bookRepository;
        this.totalBookPriceOutputBoundary = totalBookPriceOutputBoundary;
    }

    @Override
    public void calculateTotalBookPrice() {
        List<Book> books = bookRepository.getAllBooks();
        if (books == null) {
            books = new ArrayList<>(); // Tránh NullPointerException, tạo danh sách trống nếu trả về null
        }
        double totalPrice = 0;

        for (Book book : books) {
            totalPrice += book.calculateTotalPrice();
        }

        TotalBookPriceResponseData responseData = new TotalBookPriceResponseData(totalPrice);
        totalBookPriceOutputBoundary.presentTotalBookPrice(responseData);
    }

}
