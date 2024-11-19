package book;

import book.addbook.AddBookPresenter;
import book.database.BookMySQL;
import book.database.BookRepository;
import book.editbook.EditBookPresenter;
import book.ui.addbook.AddBookFormController;
import book.ui.addbook.AddBookResultForm;
import book.ui.getbooklist.MainForm;
import book.ui.getbooklist.MainFormController;
import book.ui.editbook.EditBookFormController;
import book.usecase.AddBookService;
import book.getbooklist.GetBookListPresenter;
import book.getbooklist.GetBookListInputBoundary;
import book.getbooklist.GetBookListOutputBoundary;
import book.usecase.GetBookListService;
import book.usecase.EditBookService;  // Import EditBookService

public class App {
    public static void main(String[] args) {
        // Khởi tạo các thành phần liên quan đến thêm sách
        BookMySQL db = new BookMySQL();
        BookRepository repository = new BookRepository(db);
        AddBookResultForm resultForm = new AddBookResultForm();
        AddBookPresenter addBookPresenter = new AddBookPresenter(resultForm);
        AddBookService addBookService = new AddBookService(repository, addBookPresenter);
        AddBookFormController addBookFormController = new AddBookFormController(addBookService);

        // Khởi tạo các thành phần liên quan đến việc lấy danh sách sách
        GetBookListOutputBoundary getBookListPresenter = null;  // Trước tiên, set null
        GetBookListInputBoundary getBookListService = new GetBookListService(repository, getBookListPresenter);

        // Khởi tạo EditBookFormController và EditBookPresenter
        EditBookPresenter editBookPresenter = new EditBookPresenter();
        EditBookService editBookService = new EditBookService(repository, editBookPresenter);
        EditBookFormController editBookFormController = new EditBookFormController(editBookService);

        // Tạo MainForm và truyền AddBookFormController và EditBookFormController vào
        MainForm mainForm = new MainForm(addBookFormController, editBookFormController);

        // Khởi tạo GetBookListPresenter sau khi MainForm đã được tạo
        getBookListPresenter = new GetBookListPresenter(mainForm);

        // Cập nhật GetBookListPresenter vào GetBookListService
        getBookListService = new GetBookListService(repository, getBookListPresenter);

        // Khởi tạo MainFormController và liên kết với controller của việc lấy danh sách sách
        MainFormController mainFormController = new MainFormController(getBookListService);

        // Gọi loadBooks để tải sách
        mainFormController.loadBooks();

        // Hiển thị MainForm
        mainForm.setVisible(true);
    }
}
