package book.ui.searchbook;

import book.searchbook.SearchBookInputBoundary;
import book.searchbook.SearchBookRequestData;

public class SearchBookFormController {
    private SearchBookInputBoundary searchBookService;

    public SearchBookFormController(SearchBookInputBoundary searchBookService) {
        this.searchBookService = searchBookService;
    }

    public void searchBook(String keyword) {
        SearchBookRequestData requestData = new SearchBookRequestData(keyword);
        searchBookService.searchBook(requestData);
    }

    public void openSearchBookForm() {
        SearchBookForm searchBookForm = new SearchBookForm(this);
        searchBookForm.setVisible(true);
    }
}
