package book.database;

import java.util.List;
import book.editbook.EditBookRequestData;
import book.entities.Book;

public interface BookDBBoundary {
    void saveBook(Book book); // Lưu sách mới
    List<Book> getAllBooks(); // Lấy tất cả sách
    void updateBook(EditBookRequestData editBookRequestData); // Cập nhật sách
    void deleteBook(String bookId); // Xóa sách theo ID
    public List<Book> searchBooksById(String keyword);
}
