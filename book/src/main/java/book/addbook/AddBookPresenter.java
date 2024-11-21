package book.addbook;

import book.ui.addbook.AddBookResultForm;

public class AddBookPresenter implements AddBookOutputBoundary {
    private AddBookResultForm addBookResultForm;

    public AddBookPresenter(AddBookResultForm addBookResultForm) {
        this.addBookResultForm = addBookResultForm;
    }

    @Override
    public void presentResult(AddBookResponseData addBookResponseData) {
        addBookResultForm.displayResult(addBookResponseData.message);
    }
}
