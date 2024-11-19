package book.searchbook;

import book.entities.Book;
import java.util.List;

public class SearchBookResponseData {
    private List<Book> foundBooks;

    public SearchBookResponseData(List<Book> foundBooks) {
        this.foundBooks = foundBooks;
    }

    public List<Book> getFoundBooks() {
        return foundBooks;
    }
}
