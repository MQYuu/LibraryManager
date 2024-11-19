package book.ui.editbook;

import book.editbook.EditBookInputBoundary;
import book.editbook.EditBookRequestData;

public class EditBookFormController {
        private EditBookInputBoundary editBookService;
    
        public EditBookFormController(EditBookInputBoundary editBookService) {
            this.editBookService = editBookService;
        }
    
        public void updateBook(String bookId, String entryDate, double unitPrice, int quantity, String publisher, String condition, Double tax) {
            EditBookRequestData requestData = new EditBookRequestData(bookId, entryDate, unitPrice, quantity, publisher, condition, tax);
            editBookService.editBook(requestData);
        }
    
        // Thêm phương thức displayEditBookForm
        // Phương thức hiển thị form chỉnh sửa sách
        public void displayEditBookForm(String bookId) {
        // Tạo form chỉnh sửa sách và hiển thị
        EditBookForm form = new EditBookForm(this);

        // Hiển thị form mà không điền dữ liệu vào các trường
        form.setVisible(true);
}
}
    



