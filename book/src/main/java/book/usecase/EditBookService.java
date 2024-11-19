package book.usecase;

import book.database.BookRepository;
import book.editbook.EditBookInputBoundary;
import book.editbook.EditBookOutputBoundary;
import book.editbook.EditBookRequestData;
import book.editbook.EditBookResponseData;

public class EditBookService implements EditBookInputBoundary {
    private BookRepository bookRepository; // Thay đổi sang BookRepository
    private EditBookOutputBoundary outputBoundary;

    public EditBookService(BookRepository bookRepository, EditBookOutputBoundary outputBoundary) {
        this.bookRepository = bookRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void editBook(EditBookRequestData requestData) {
        // Gọi phương thức editBook từ BookRepository
        bookRepository.editBook(requestData);
        
        // Giả định việc cập nhật luôn thành công nếu không có lỗi xảy ra
        EditBookResponseData responseData = new EditBookResponseData(
                true, 
                "Update successful."
        );
        
        // Trình bày kết quả
        outputBoundary.presentEditBookResult(responseData);
    }
}
