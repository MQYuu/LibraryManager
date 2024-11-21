package book.getbooklist;

import book.ui.getbooklist.MainForm;

public class GetBookListPresenter implements GetBookListOutputBoundary {
    private MainForm mainForm;

    public GetBookListPresenter(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    public void presentBookList(GetBookListResponseData getBookListResponseData) {
        // Truyền danh sách sách từ getBookListResponseData vào MainForm để hiển thị
        mainForm.displayBookList(getBookListResponseData.getBookList());
    }
}
