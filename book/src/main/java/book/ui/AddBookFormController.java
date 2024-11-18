package book.ui;

import book.addbookinterface.AddBookInputBoundary;
import book.addbookinterface.AddBookRequestData;

public class AddBookFormController {
    private AddBookInputBoundary addBookInputBoundary;

    public AddBookFormController(AddBookInputBoundary addBookInputBoundary) {
        this.addBookInputBoundary = addBookInputBoundary;
    }

    public void displayAddBookForm() {
        AddBookForm form = new AddBookForm(this);
        form.setVisible(true);
    }

    public void submitAddBookForm(AddBookRequestData requestData) {
        addBookInputBoundary.executeAdd(requestData);
    }
}
