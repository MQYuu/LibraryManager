package book.ui.getbooklist;

import book.getbooklist.GetBookListInputBoundary;

public class MainFormController {
    private GetBookListInputBoundary getBookListInputBoundary;

    public MainFormController(GetBookListInputBoundary getBookListInputBoundary) {
        this.getBookListInputBoundary = getBookListInputBoundary;
    }

    public void loadBooks() {
        getBookListInputBoundary.getBookList();  // Gọi use case để lấy danh sách sách
    }
}
