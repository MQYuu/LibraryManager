package book.deletebook;

import book.ui.deletebook.DeleteBookResultForm;

public class DeleteBookPresenter implements DeleteBookOutputBoundary {
    private DeleteBookResultForm deleteBookResultForm;

    public DeleteBookPresenter() {
        this.deleteBookResultForm = new DeleteBookResultForm();
    }

    @Override
    public void presentDeleteBookResult(DeleteBookResponseData deleteBookResponseData) {
        deleteBookResultForm.displayResult(deleteBookResponseData.getMessage(), deleteBookResponseData.isSuccess());
    }
}
