package book.getbooklist;

import book.ui.getbooklist.MainForm;

public class GetBookListPresenter implements GetBookListOutputBoundary {
    private MainForm mainForm;

    public GetBookListPresenter(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    public void presentBookList(GetBookListResponseData responseData) {
        // Truyền danh sách sách từ responseData vào MainForm để hiển thị
        mainForm.displayBookList(responseData.getBookList());
    }
}
