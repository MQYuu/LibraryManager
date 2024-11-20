package book.usecase;

import java.util.ArrayList;
import java.util.List;

import book.database.BookRepository;
import book.entities.Book;
import book.printbooklist.PrintBookListInputBoundary;
import book.printbooklist.PrintBookOutputBoundary;
import book.printbooklist.PrintBookResponseData;

import book.entities.ReferenceBook;
import book.entities.TextBook;

public class PrintBookService implements PrintBookListInputBoundary {
    private BookRepository bookRepository;
    private PrintBookOutputBoundary printBookOutputBoundary;

    public PrintBookService(BookRepository bookRepository, PrintBookOutputBoundary printBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.printBookOutputBoundary = printBookOutputBoundary;
    }

    @Override
    public void printBook() {
        List<Book> books = bookRepository.getAllBooks();
        if (books == null) {
            books = new ArrayList<>(); // Tránh NullPointerException
        }
        List<PrintBookResponseData> printBookResponseDataList = new ArrayList<>();

        for (Book book : books) {
            if (book == null) {
                continue; // Nếu có bất kỳ sách nào bị null, bỏ qua nó
            }

            // Tạo đối tượng PrintBookResponseData từ thông tin sách
            String bookId = book.getBookId();
            String entryDate = book.getEntryDate().toString(); // Chuyển ngày nhập thành chuỗi
            double unitPrice = book.getUnitPrice();
            int quantity = book.getQuantity();
            String publisher = book.getPublisher();
            double tax = 0;
            String condition = "";

            // Nếu là ReferenceBook, lấy thuế, nếu là TextBook, lấy tình trạng
            if (book instanceof ReferenceBook) {
                tax = ((ReferenceBook) book).getTax();
            } else if (book instanceof TextBook) {
                condition = ((TextBook) book).getCondition();
            }

            // Thêm thông tin sách vào danh sách
            printBookResponseDataList
                    .add(new PrintBookResponseData(bookId, entryDate, unitPrice, quantity, publisher, tax, condition));
        }

        // Trình bày kết quả qua giao diện
        printBookOutputBoundary.presentPrintBookResult(printBookResponseDataList);
    }

}
