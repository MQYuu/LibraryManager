package book.ui.editbook;

import book.editbook.EditBookInputBoundary;
import book.editbook.EditBookRequestData;

public class EditBookFormController {
    private EditBookInputBoundary editBookInputBoundary;

    public EditBookFormController(EditBookInputBoundary editBookInputBoundary) {
        this.editBookInputBoundary = editBookInputBoundary;
    }

    public void updateBook(String bookId, String entryDate, double unitPrice, int quantity, String publisher,
            String condition, Double tax) {
        EditBookRequestData requestData = new EditBookRequestData(bookId, entryDate, unitPrice, quantity, publisher,
                condition, tax);
        editBookInputBoundary.editBook(requestData);
    }

    // Phương thức hiển thị form chỉnh sửa sách
    public void displayEditBookForm(String bookId) {
        // Tạo form chỉnh sửa sách và hiển thị
        EditBookForm form = new EditBookForm(this);

        // Hiển thị form
        form.setVisible(true);
    }
}
