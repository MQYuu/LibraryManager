package book.database;

import java.util.List;
import java.util.stream.Collectors;

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
        bookDBBoundary.deleteBook(bookId);
        return true; // Đây là ví dụ giả sử luôn xóa thành công
    }
        // Phương thức tìm kiếm sách theo từ khóa
    public List<Book> searchBooksByKeyword(String keyword) {
        // Lấy tất cả sách từ BookDBBoundary và lọc theo từ khóa
        return bookDBBoundary.getAllBooks().stream()
            .filter(book -> book.getBookId().contains(keyword) || book.getPublisher().contains(keyword))
            .collect(Collectors.toList());
    }
}
