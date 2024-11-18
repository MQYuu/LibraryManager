package book.ui.addbook;

import book.addbook.AddBookInputBoundary;
import book.addbook.AddBookRequestData;

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
