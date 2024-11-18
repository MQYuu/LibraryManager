package usecase;
import java.util.ArrayList;
import java.util.List;

import bookdata.Book;
import bookdata.Textbook;

public class ExportTextbooksByPublisherUseCase {
    private List<Book> books;

    public ExportTextbooksByPublisherUseCase(List<Book> books) {
        this.books = books;
    }

    public List<Textbook> execute(String publisher) {
        List<Textbook> result = new ArrayList<>();
        for (Book book : books) {
            if (book instanceof Textbook && book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add((Textbook) book);
            }
        }
        return result;
    }
}
