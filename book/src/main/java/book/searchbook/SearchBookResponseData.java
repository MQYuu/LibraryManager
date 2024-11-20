package book.searchbook;

import book.entities.Book;
import java.util.List;

public class SearchBookResponseData {
    private List<Book> foundBookById;

    public SearchBookResponseData(List<Book> foundBookById) {
        this.foundBookById = foundBookById;
    }

    public List<Book> getFoundBooks() {
        return foundBookById;
    }

    @Override
    public String toString() {
        return "SearchBookResponseData{" +
                "foundBooks=" + foundBookById+
                '}';
    }

}
