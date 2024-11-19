package book.ui.searchbook;

import book.searchbook.SearchBookInputBoundary;
import book.searchbook.SearchBookRequestData;

public class SearchBookFormController {
    private SearchBookInputBoundary searchBookInputBoundary;

    public SearchBookFormController(SearchBookInputBoundary searchBookInputBoundary) {
        this.searchBookInputBoundary = searchBookInputBoundary;
    }

    public void searchBook(String bookId) {
        SearchBookRequestData requestData = new SearchBookRequestData(bookId);
        searchBookInputBoundary.searchBook(requestData);
    }

    public void openSearchBookForm() {
        SearchBookForm searchBookForm = new SearchBookForm(this);
        searchBookForm.setVisible(true);
    }
}
