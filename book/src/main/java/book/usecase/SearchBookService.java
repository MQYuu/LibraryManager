package book.usecase;

import book.database.BookRepository;
import book.searchbook.SearchBookInputBoundary;
import book.searchbook.SearchBookOutputBoundary;
import book.searchbook.SearchBookRequestData;
import book.searchbook.SearchBookResponseData;
import book.entities.Book;
import java.util.List;

public class SearchBookService implements SearchBookInputBoundary {
    private BookRepository bookRepository;
    private SearchBookOutputBoundary searchBookOutputBoundary;

    public SearchBookService(BookRepository bookRepository, SearchBookOutputBoundary searchBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.searchBookOutputBoundary = searchBookOutputBoundary;
    }

    @Override
    public void searchBook(SearchBookRequestData requestData) {
        String bookId = requestData.getBookId();
        List<Book> foundBooks = bookRepository.searchBooksById(bookId);
        SearchBookResponseData responseData = new SearchBookResponseData(foundBooks);
        searchBookOutputBoundary.presentSearchBookResults(responseData);
    }
}
