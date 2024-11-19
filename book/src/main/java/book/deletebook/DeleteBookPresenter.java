package book.deletebook;

import book.ui.deletebook.DeleteBookResultForm;

public class DeleteBookPresenter implements DeleteBookOutputBoundary {
    private DeleteBookResultForm resultForm;

    public DeleteBookPresenter() {
        this.resultForm = new DeleteBookResultForm();
    }

    @Override
    public void presentDeleteBookResult(DeleteBookResponseData responseData) {
        resultForm.displayResult(responseData.getMessage(), responseData.isSuccess());
    }
}
