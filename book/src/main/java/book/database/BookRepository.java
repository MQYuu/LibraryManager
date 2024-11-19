package book.database;

import java.util.List;

import book.editbook.EditBookRequestData;
import book.entities.Book;

    public class BookRepository {
    private BookDBBoundary database;

    public BookRepository(BookDBBoundary database) {
        this.database = database;
    }

    public void addBook(Book book) {
        database.saveBook(book);
    }

    public List<Book> getAllBooks() {
        return database.getAllBooks();
    }
    public void editBook(EditBookRequestData editBookRequestData) {
        database.updateBook(editBookRequestData); // Gọi đến updateBook trong BookDBBoundary
    }
    public boolean deleteBook(String bookId) {
        // Thực hiện xóa sách từ cơ sở dữ liệu hoặc danh sách
        // Trả về true nếu thành công, false nếu không tìm thấy sách
        return true; // Đây là ví dụ giả sử luôn xóa thành công
    }
}
