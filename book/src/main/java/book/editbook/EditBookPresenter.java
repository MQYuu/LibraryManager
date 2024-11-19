package book.editbook;

import book.ui.editbook.EditBookResultForm;

public class EditBookPresenter implements EditBookOutputBoundary {
    private EditBookResultForm resultForm;

    public EditBookPresenter() {
        this.resultForm = new EditBookResultForm();
    }

    @Override
    public void presentEditBookResult(EditBookResponseData responseData) {
        resultForm.displayResult(responseData.getMessage(), responseData.isSuccess());
    }
}




