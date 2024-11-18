package book;

import book.addbookinterface.AddBookPresenter;
import book.database.AddBookMySQL;
import book.ui.AddBookFormController;
import book.ui.AddBookResultForm;
import book.ui.MainForm;
import book.usecase.AddBookService;
import book.usecase.BookRepository;

public class App {
        public static void main(String[] args) {
        AddBookMySQL db = new AddBookMySQL();
        BookRepository repository = new BookRepository(db);
        AddBookResultForm resultForm = new AddBookResultForm();
        AddBookPresenter presenter = new AddBookPresenter(resultForm);
        AddBookService service = new AddBookService(repository, presenter);
        AddBookFormController controller = new AddBookFormController(service);
    
        MainForm mainForm = new MainForm(controller);
        mainForm.setVisible(true);
    }
}
