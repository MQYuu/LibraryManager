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
    private SearchBookOutputBoundary outputBoundary;

    public SearchBookService(BookRepository bookRepository, SearchBookOutputBoundary outputBoundary) {
        this.bookRepository = bookRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void searchBook(SearchBookRequestData requestData) {
        String keyword = requestData.getKeyword();
        List<Book> foundBooks = bookRepository.searchBooksByKeyword(keyword);
        SearchBookResponseData responseData = new SearchBookResponseData(foundBooks);
        outputBoundary.presentSearchBookResults(responseData);
    }
}
