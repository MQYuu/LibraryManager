package book;

import book.addbook.AddBookPresenter;
import book.database.BookMySQL;
import book.database.BookRepository;
import book.ui.AddBookFormController;
import book.ui.AddBookResultForm;
import book.ui.MainForm;
import book.usecase.AddBookService;

public class App {
        public static void main(String[] args) {
        BookMySQL db = new BookMySQL();
        BookRepository repository = new BookRepository(db);
        AddBookResultForm resultForm = new AddBookResultForm();
        AddBookPresenter presenter = new AddBookPresenter(resultForm);
        AddBookService service = new AddBookService(repository, presenter);
        AddBookFormController controller = new AddBookFormController(service);
    
        MainForm mainForm = new MainForm(controller);
        mainForm.setVisible(true);
    }
}
