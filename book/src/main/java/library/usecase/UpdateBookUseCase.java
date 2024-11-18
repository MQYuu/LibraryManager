package library.usecase;

import java.util.Date;
import java.util.List;

import library.bookdata.Book;
import library.bookdata.Textbook;
import library.bookdata.ReferenceBook;

public class UpdateBookUseCase {
    private List<Book> books;

    public UpdateBookUseCase(List<Book> books) {
        this.books = books;
    }

    public void execute(String bookId, Date dateAdded, double newUnitPrice, int newQuantity, String publisher, String newCondition, double newTax) {
        // Duyệt qua tất cả sách
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                // Cập nhật thông tin chung
                book.setDateAdded(dateAdded);
                book.setUnitPrice(newUnitPrice);
                book.setQuantity(newQuantity);
                book.setPublisher(publisher);

                // Kiểm tra loại sách và cập nhật thông tin theo loại
                if (book instanceof Textbook) {
                    // Cập nhật tình trạng sách cho sách giáo khoa
                    ((Textbook) book).setCondition(newCondition);
                } else if (book instanceof ReferenceBook) {
                    // Cập nhật thuế cho sách tham khảo
                    ((ReferenceBook) book).setTax(newTax);
                }
                break;  // Đã tìm thấy sách, thoát khỏi vòng lặp
            }
        }
    }
}
