package book.usecase;

import java.util.List;

import book.database.BookDBBoundary;
import book.entities.Book;
import book.getbooklist.GetBookListInputBoundary;
import book.getbooklist.GetBookListOutputBoundary;
import book.getbooklist.GetBookListResponseData;

// class này triển khai interface getbooklistinputboundary và sử dụng bookrepository để lấy danh sách từ database
public class GetBookListService implements GetBookListInputBoundary {
    private BookDBBoundary bookRepository;
    private GetBookListOutputBoundary getListBookOutputBoundary;

    public GetBookListService(BookDBBoundary bookRepository, GetBookListOutputBoundary getListBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.getListBookOutputBoundary = getListBookOutputBoundary;
    }

    @Override
    public void getBookList() {
        List<Book> books = bookRepository.getAllBooks();
        GetBookListResponseData responseData = new GetBookListResponseData(books);
        getListBookOutputBoundary.presentBookList(responseData);

    }
}

