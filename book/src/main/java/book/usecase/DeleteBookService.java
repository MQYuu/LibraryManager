package book.usecase;

import book.database.BookDBBoundary;
import book.deletebook.DeleteBookInputBoundary;
import book.deletebook.DeleteBookOutputBoundary;
import book.deletebook.DeleteBookRequestData;
import book.deletebook.DeleteBookResponseData;

public class DeleteBookService implements DeleteBookInputBoundary {
    private BookDBBoundary bookDBBoundary;
    private DeleteBookOutputBoundary deleteBookOutputBoundary;

    public DeleteBookService(BookDBBoundary bookDBBoundary, DeleteBookOutputBoundary deleteBookOutputBoundary) {
        this.bookDBBoundary = bookDBBoundary;
        this.deleteBookOutputBoundary = deleteBookOutputBoundary;
    }

    @Override
    public void deleteBook(DeleteBookRequestData requestData) {
        try {
            // Gọi phương thức deleteBook để xóa sách
            bookDBBoundary.deleteBook(requestData.getBookId());
            
            // Nếu không có ngoại lệ, sách đã bị xóa thành công
            DeleteBookResponseData responseData = new DeleteBookResponseData(true, "Xóa sách thành công.");
            deleteBookOutputBoundary.presentDeleteBookResult(responseData);
            
        } catch (Exception e) {
            // Xử lý khi ID sách không tồn tại hoặc xảy ra lỗi khác
            DeleteBookResponseData responseData = new DeleteBookResponseData(false, "Id sách không tồn tại!");
            deleteBookOutputBoundary.presentDeleteBookResult(responseData);
        }
    }
}
