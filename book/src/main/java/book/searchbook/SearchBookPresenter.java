package book.searchbook;

import book.entities.Book;
import book.ui.searchbook.SearchBookResultForm;
import java.util.List;

public class SearchBookPresenter implements SearchBookOutputBoundary {
    private SearchBookResultForm resultForm;

    public SearchBookPresenter() {
        this.resultForm = new SearchBookResultForm();
    }

    @Override
    public void presentSearchBookResults(SearchBookResponseData responseData) {
        List<Book> books = responseData.getFoundBooks();
        resultForm.displayResults(books); // Hiển thị kết quả trên form kết quả
    }
}
