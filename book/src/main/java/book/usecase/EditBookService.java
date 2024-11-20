package book.usecase;

import book.database.BookRepository;
import book.editbook.EditBookInputBoundary;
import book.editbook.EditBookOutputBoundary;
import book.editbook.EditBookRequestData;
import book.editbook.EditBookResponseData;

public class EditBookService implements EditBookInputBoundary {
    private BookRepository bookRepository; // Thay đổi sang BookRepository
    private EditBookOutputBoundary editBookOutputBoundary;

    public EditBookService(BookRepository bookRepository, EditBookOutputBoundary editBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.editBookOutputBoundary = editBookOutputBoundary;
    }

    public void editBook(EditBookRequestData requestData) {
        try {
            // Kiểm tra nếu bookId rỗng (dữ liệu không hợp lệ)
            if (requestData.getBookId() == null || requestData.getBookId().isEmpty()) {
                EditBookResponseData responseData = new EditBookResponseData(false, "Invalid book ID.");
                editBookOutputBoundary.presentEditBookResult(responseData);
                return;
            }
    
            // Gọi phương thức editBook từ BookRepository
            bookRepository.editBook(requestData);
            
            // Giả định việc cập nhật luôn thành công nếu không có lỗi xảy ra
            EditBookResponseData responseData = new EditBookResponseData(true, "Update successful.");
            editBookOutputBoundary.presentEditBookResult(responseData);
        } catch (RuntimeException e) {
            // Xử lý khi có lỗi trong quá trình cập nhật sách
            EditBookResponseData responseData = new EditBookResponseData(false, "Error updating book: " + e.getMessage());
            editBookOutputBoundary.presentEditBookResult(responseData);
        }
    }
    
    
}
