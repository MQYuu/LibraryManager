package library.usecase;
import java.util.ArrayList;
import java.util.List;

import library.bookdata.Book;

public class SearchByIDUseCase {
    private List<Book> books;

    public SearchByIDUseCase(List<Book> books) {
        this.books = books;
    }

    public List<Book> execute(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getBookId().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }
}
