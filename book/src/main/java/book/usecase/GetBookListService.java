package book.usecase;

import java.util.List;

import book.database.BookRepository;
import book.entities.Book;
import book.getbooklist.GetBookListInputBoundary;
import book.getbooklist.GetBookListOutputBoundary;
import book.getbooklist.GetBookListResponseData;

// class này triển khai interface getbooklistinputboundary và sử dụng bookrepository để lấy danh sách từ database
public class GetBookListService implements GetBookListInputBoundary {
    private BookRepository bookRepository;
    private GetBookListOutputBoundary outputBoundary;

    public GetBookListService(BookRepository bookRepository, GetBookListOutputBoundary outputBoundary) {
        this.bookRepository = bookRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void getBookList() {
        List<Book> books = bookRepository.getAllBooks();
        GetBookListResponseData responseData = new GetBookListResponseData(books);
        outputBoundary.presentBookList(responseData);
    }
}

