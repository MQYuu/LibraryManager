package library.usecase;

import java.util.ArrayList;
import java.util.List;

import library.bookdata.Book;
import library.bookdata.Textbook;

public class ExportTextbooksByPublisherUseCase {
    private List<Book> books;

    public ExportTextbooksByPublisherUseCase(List<Book> books) {
        this.books = books;
    }

    // Phương thức thực hiện xuất thông tin sách giáo khoa theo nhà xuất bản
    public List<Textbook> execute(String publisher) {
        List<Textbook> result = new ArrayList<>();
        for (Book book : books) {
            if (book instanceof Textbook && book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add((Textbook) book);
            }
        }

        return result; // Trả về danh sách sách giáo khoa đầy đủ thông tin
    }
}
