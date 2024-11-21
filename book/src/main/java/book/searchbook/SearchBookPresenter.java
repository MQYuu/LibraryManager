package book.searchbook;

import book.entities.Book;
import book.ui.searchbook.SearchBookResultForm;
import java.util.List;

public class SearchBookPresenter implements SearchBookOutputBoundary {
    private SearchBookResultForm searchBookResultForm;

    public SearchBookPresenter() {
        this.searchBookResultForm = new SearchBookResultForm();
    }

    @Override
    public void presentSearchBookResults(SearchBookResponseData searchBookResponseData) {
        List<Book> books = searchBookResponseData.getFoundBooks();
        searchBookResultForm.displayResults(books); // Hiển thị kết quả trên form kết quả
    }
}
