package book.ui.deletebook;

import book.deletebook.DeleteBookInputBoundary;
import book.deletebook.DeleteBookRequestData;

public class DeleteBookFormController {
    private DeleteBookInputBoundary deleteBookService;

    public DeleteBookFormController(DeleteBookInputBoundary deleteBookService) {
        this.deleteBookService = deleteBookService;
    }

    public void deleteBook(String bookId) {
        DeleteBookRequestData requestData = new DeleteBookRequestData(bookId);
        deleteBookService.deleteBook(requestData);
    }

    // Phương thức mở form xóa sách
    public void openDeleteBookForm() {
        DeleteBookForm deleteBookForm = new DeleteBookForm(this);
        deleteBookForm.setVisible(true);
    }
}
