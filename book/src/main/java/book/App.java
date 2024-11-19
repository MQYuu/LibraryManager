package book;

import book.database.BookMySQL;
import book.database.BookRepository;

import book.addbook.AddBookPresenter;
import book.ui.addbook.AddBookFormController;
import book.ui.addbook.AddBookResultForm;
import book.usecase.AddBookService;

import book.deletebook.DeleteBookPresenter;
import book.ui.deletebook.DeleteBookFormController;
import book.usecase.DeleteBookService;

import book.editbook.EditBookPresenter;
import book.ui.editbook.EditBookFormController;
import book.usecase.EditBookService;

import book.usecase.GetBookListService;
import book.ui.getbooklist.MainForm;
import book.ui.getbooklist.MainFormController;
import book.getbooklist.GetBookListPresenter;
import book.getbooklist.GetBookListOutputBoundary;
import book.getbooklist.GetBookListInputBoundary;

import book.searchbook.SearchBookPresenter;
import book.ui.searchbook.SearchBookFormController;
import book.usecase.SearchBookService;

public class App {
    public static void main(String[] args) {
        // Khởi tạo các thành phần liên quan đến thêm sách
        BookMySQL db = new BookMySQL();
        BookRepository repository = new BookRepository(db);
        AddBookResultForm resultForm = new AddBookResultForm();
        AddBookPresenter addBookPresenter = new AddBookPresenter(resultForm);
        AddBookService addBookService = new AddBookService(repository, addBookPresenter);
        AddBookFormController addBookFormController = new AddBookFormController(addBookService);

        // Khởi tạo các thành phần liên quan đến lấy danh sách sách
        GetBookListOutputBoundary getBookListPresenter = null;  // Để tạm thời là null
        GetBookListInputBoundary getBookListService = new GetBookListService(repository, getBookListPresenter);

        // Khởi tạo các thành phần liên quan đến chỉnh sửa sách
        EditBookPresenter editBookPresenter = new EditBookPresenter();
        EditBookService editBookService = new EditBookService(repository, editBookPresenter);
        EditBookFormController editBookFormController = new EditBookFormController(editBookService);

        // Khởi tạo các thành phần liên quan đến xóa sách
        DeleteBookPresenter deleteBookPresenter = new DeleteBookPresenter();
        DeleteBookService deleteBookService = new DeleteBookService(repository, deleteBookPresenter);
        DeleteBookFormController deleteBookFormController = new DeleteBookFormController(deleteBookService);

        // Khởi tạo các thành phần liên quan đến tìm kiếm sách
        SearchBookPresenter searchBookPresenter = new SearchBookPresenter();
        SearchBookService searchBookService = new SearchBookService(repository, searchBookPresenter);
        SearchBookFormController searchBookFormController = new SearchBookFormController(searchBookService);

        // Tạo MainForm và truyền các controller vào
        MainForm mainForm = new MainForm(addBookFormController, editBookFormController, deleteBookFormController, searchBookFormController);

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
