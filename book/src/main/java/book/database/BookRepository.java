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
        // Phương thức tìm kiếm sách theo id
        public List<Book> searchBooksById(String bookId) {
            // Lọc danh sách sách chỉ dựa vào book_id
            return bookDBBoundary.getAllBooks().stream()
                .filter(book -> book.getBookId().equals(bookId))  // So sánh chính xác book_id
                .collect(Collectors.toList());  // Trả về danh sách sách khớp với keyword
        }
        
}
