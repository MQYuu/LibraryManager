package usecase;
import java.util.ArrayList;
import java.util.List;

import bookdata.Book;

public class SearchByPublisherUseCase {
    private List<Book> books;

    public SearchByPublisherUseCase(List<Book> books) {
        this.books = books;
    }

    public List<Book> execute(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }
}
