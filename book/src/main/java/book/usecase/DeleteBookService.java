package book.usecase;

import book.database.BookRepository;
import book.deletebook.DeleteBookInputBoundary;
import book.deletebook.DeleteBookOutputBoundary;
import book.deletebook.DeleteBookRequestData;
import book.deletebook.DeleteBookResponseData;

public class DeleteBookService implements DeleteBookInputBoundary {
    private BookRepository bookRepository;
    private DeleteBookOutputBoundary deleteBookOutputBoundary;

    public DeleteBookService(BookRepository bookRepository, DeleteBookOutputBoundary deleteBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.deleteBookOutputBoundary = deleteBookOutputBoundary;
    }

    @Override
    public void deleteBook(DeleteBookRequestData requestData) {
        // Gọi repository để xóa sách
        boolean success = bookRepository.deleteBook(requestData.getBookId());
        
        // Tạo dữ liệu phản hồi
        DeleteBookResponseData responseData = new DeleteBookResponseData(
                success,
                success ? "Xóa sách thành công." : "Id sách không tồn tại!"
        );
        
        // Trình bày kết quả
        deleteBookOutputBoundary.presentDeleteBookResult(responseData);
    }
}

