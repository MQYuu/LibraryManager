package book.ui.getbooklist;

import book.getbooklist.GetBookListInputBoundary;
import book.getbooklist.GetBookListOutputBoundary;

public class MainFormController {
    private GetBookListInputBoundary getBooksService;
    private GetBookListOutputBoundary presenter;  // Để trình bày kết quả

    public MainFormController(GetBookListInputBoundary getBooksService, GetBookListOutputBoundary presenter) {
        this.getBooksService = getBooksService;
        this.presenter = presenter;
    }

    public void loadBooks() {
        getBooksService.getBookList();  // Gọi use case để lấy danh sách sách
    }
}
