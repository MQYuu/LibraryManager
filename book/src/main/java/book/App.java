package book;

import book.addbook.AddBookPresenter;
import book.database.BookMySQL;
import book.database.BookRepository;
import book.ui.MainForm;
import book.ui.addbook.AddBookFormController;
import book.ui.addbook.AddBookResultForm;
import book.ui.getbooklist.MainFormController;
import book.usecase.AddBookService;
import book.getbooklist.GetBookListPresenter;
import book.getbooklist.GetBookListInputBoundary;
import book.getbooklist.GetBookListOutputBoundary;
import book.usecase.GetBookListService;

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

        // Tạo MainForm và truyền AddBookFormController vào
        MainForm mainForm = new MainForm(addBookFormController);

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
