package book.editbook;

import book.ui.editbook.EditBookResultForm;

public class EditBookPresenter implements EditBookOutputBoundary {
    private EditBookResultForm editBookResultForm;

    public EditBookPresenter() {
        this.editBookResultForm = new EditBookResultForm();
    }

    @Override
    public void presentEditBookResult(EditBookResponseData editBookResponseData) {
        editBookResultForm.displayResult(editBookResponseData.getMessage(), editBookResponseData.isSuccess());
    }
}




