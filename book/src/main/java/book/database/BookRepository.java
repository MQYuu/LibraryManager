package book.database;

import java.util.List;

import book.editbook.EditBookRequestData;
import book.entities.Book;

    public class BookRepository {
    private BookDBBoundary bookDBBoundary;

    public BookRepository(BookDBBoundary bookDBBoundary) {
        this.bookDBBoundary = bookDBBoundary;
    }

    public void addBook(Book book) {
        bookDBBoundary.saveBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDBBoundary.getAllBooks();
    }
    public void editBook(EditBookRequestData editBookRequestData) {
        bookDBBoundary.updateBook(editBookRequestData); // Gọi đến updateBook trong BookDBBoundary
    }
    public boolean deleteBook(String bookId) {
        // Thực hiện xóa sách từ cơ sở dữ liệu hoặc danh sách
        // Trả về true nếu thành công, false nếu không tìm thấy sách
        bookDBBoundary.deleteBook(bookId);
        return true; // Đây là ví dụ giả sử luôn xóa thành công
    }
}
