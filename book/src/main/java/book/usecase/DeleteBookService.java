package book.usecase;

import book.database.BookRepository;
import book.deletebook.DeleteBookInputBoundary;
import book.deletebook.DeleteBookOutputBoundary;
import book.deletebook.DeleteBookRequestData;
import book.deletebook.DeleteBookResponseData;

public class DeleteBookService implements DeleteBookInputBoundary {
    private BookRepository bookRepository;
    private DeleteBookOutputBoundary outputBoundary;

    public DeleteBookService(BookRepository bookRepository, DeleteBookOutputBoundary outputBoundary) {
        this.bookRepository = bookRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void deleteBook(DeleteBookRequestData requestData) {
        // Gọi repository để xóa sách
        boolean success = bookRepository.deleteBook(requestData.getBookId());
        
        // Tạo dữ liệu phản hồi
        DeleteBookResponseData responseData = new DeleteBookResponseData(
                success,
                success ? "Xóa sách thành công." : "Không thể xóa sách."
        );
        
        // Trình bày kết quả
        outputBoundary.presentDeleteBookResult(responseData);
    }
}

