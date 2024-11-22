package book.usecase;

import book.database.BookDBBoundary;
import book.editbook.EditBookInputBoundary;
import book.editbook.EditBookOutputBoundary;
import book.editbook.EditBookRequestData;
import book.editbook.EditBookResponseData;

public class EditBookService implements EditBookInputBoundary {
    private BookDBBoundary BookDBBoundary; // Thay đổi sang BookDBBoundary
    private EditBookOutputBoundary editBookOutputBoundary;

    public EditBookService(BookDBBoundary BookDBBoundary, EditBookOutputBoundary editBookOutputBoundary) {
        this.BookDBBoundary = BookDBBoundary;
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
    
            // Gọi phương thức editBook từ BookDBBoundary
            BookDBBoundary.updateBook(requestData);
            
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
