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

import book.searchbook.SearchBookPresenter;
import book.ui.searchbook.SearchBookFormController;
import book.usecase.SearchBookService;

import book.usecase.PrintBookService;
import book.printbooklist.PrintBookListInputBoundary;
import book.printbooklist.PrintBookOutputBoundary;
import book.printbooklist.PrintBookPresenter;
import book.ui.printbook.PrintBookFormController;
import book.ui.printbook.PrintBookResultForm;

import book.totalbookprice.TotalBookPriceInputBoundary;
import book.totalbookprice.TotalBookPriceOutputBoundary;
import book.totalbookprice.TotalBookPricePresenter;
import book.ui.totalbookprice.TotalBookPriceFormController; // Import thêm TotalBookPriceFormController
import book.usecase.TotalBookPriceService;  // Import thêm TotalBookPriceService
import book.ui.totalbookprice.TotalBookPriceResultForm;

import book.ui.averageunitprice.AverageUnitPriceFormController; // Import AverageUnitPriceFormController
import book.ui.averageunitprice.AverageUnitPriceResultForm;
import book.usecase.AverageUnitPriceService;  // Import AverageUnitPriceService
import book.averageunitprice.AverageUnitPricePresenter; // Import AverageUnitPricePresenter

// Import các lớp liên quan đến xuất sách
import book.ui.exportbook.ExportBookFormController;
import book.usecase.ExportBookService;
import book.exportbook.ExportBookPresenter;
import book.ui.exportbook.ExportBookResultForm;

public class App {
    public static void main(String[] args) {
        // Khởi tạo các thành phần cần thiết
        BookMySQL db = new BookMySQL(); // Khởi tạo BookMySQL cho kết nối cơ sở dữ liệu
        BookRepository repository = new BookRepository(db); // Khởi tạo BookRepository với db

        // Khởi tạo các controller và dịch vụ
        AddBookFormController addBookFormController = createAddBookController(repository);
        EditBookFormController editBookFormController = createEditBookController(repository);
        DeleteBookFormController deleteBookFormController = createDeleteBookController(repository);
        SearchBookFormController searchBookFormController = createSearchBookController(repository);

        // Khởi tạo PrintBookFormController và các thành phần liên quan đến in sách
        PrintBookFormController printBookFormController = createPrintBookController(repository);

        // Khởi tạo TotalBookPriceFormController và các thành phần liên quan đến tính tổng giá sách
        TotalBookPriceFormController totalBookPriceFormController = createTotalBookPriceController(repository);

        // Khởi tạo AverageUnitPriceFormController và các thành phần liên quan đến tính trung bình đơn giá sách tham khảo
        AverageUnitPriceFormController averageUnitPriceFormController = createAverageUnitPriceController(repository);

        // Khởi tạo ExportBookFormController và các thành phần liên quan đến xuất sách
        ExportBookFormController exportBookFormController = createExportBookController(repository);

        // Tạo MainForm và liên kết với các controller
        MainForm mainForm = new MainForm(
                addBookFormController, 
                editBookFormController, 
                deleteBookFormController,
                searchBookFormController, 
                printBookFormController,
                totalBookPriceFormController, // Thêm TotalBookPriceFormController vào MainForm
                averageUnitPriceFormController, // Thêm AverageUnitPriceFormController vào MainForm
                exportBookFormController // Thêm ExportBookFormController vào MainForm
        );

        // Tạo và khởi tạo GetBookListService
        GetBookListPresenter getBookListPresenter = new GetBookListPresenter(mainForm);
        GetBookListService getBookListService = new GetBookListService(repository, getBookListPresenter);

        // Khởi tạo MainFormController
        MainFormController mainFormController = new MainFormController(getBookListService);

        // Gọi loadBooks để tải sách 
        mainFormController.loadBooks();
        //Hiển thị MainForm
        mainForm.setVisible(true);
    }

    // Các phương thức tạo controller

    private static AddBookFormController createAddBookController(BookRepository repository) {
        AddBookResultForm resultForm = new AddBookResultForm();
        AddBookPresenter addBookPresenter = new AddBookPresenter(resultForm);
        AddBookService addBookService = new AddBookService(repository, addBookPresenter);
        return new AddBookFormController(addBookService);
    }

    private static EditBookFormController createEditBookController(BookRepository repository) {
        EditBookPresenter editBookPresenter = new EditBookPresenter();
        EditBookService editBookService = new EditBookService(repository, editBookPresenter);
        return new EditBookFormController(editBookService);
    }

    private static DeleteBookFormController createDeleteBookController(BookRepository repository) {
        DeleteBookPresenter deleteBookPresenter = new DeleteBookPresenter();
        DeleteBookService deleteBookService = new DeleteBookService(repository, deleteBookPresenter);
        return new DeleteBookFormController(deleteBookService);
    }

    private static SearchBookFormController createSearchBookController(BookRepository repository) {
        SearchBookPresenter searchBookPresenter = new SearchBookPresenter();
        SearchBookService searchBookService = new SearchBookService(repository, searchBookPresenter);
        return new SearchBookFormController(searchBookService);
    }

    // Phương thức tạo PrintBookFormController
    private static PrintBookFormController createPrintBookController(BookRepository repository) {
        PrintBookResultForm printBookResultForm = new PrintBookResultForm();
        PrintBookOutputBoundary printBookOutputBoundary = new PrintBookPresenter(printBookResultForm);
        PrintBookListInputBoundary printBookService = new PrintBookService(repository, printBookOutputBoundary);
        return new PrintBookFormController(printBookService);
    }

    // Phương thức tạo TotalBookPriceFormController
    private static TotalBookPriceFormController createTotalBookPriceController(BookRepository repository) {
        TotalBookPriceResultForm totalBookPriceResultForm = new TotalBookPriceResultForm();
        TotalBookPriceOutputBoundary totalBookPriceOutputBoundary = new TotalBookPricePresenter(totalBookPriceResultForm);
        TotalBookPriceInputBoundary totalBookPriceService = new TotalBookPriceService(repository, totalBookPriceOutputBoundary);
        return new TotalBookPriceFormController(totalBookPriceService);
    }

    // Phương thức tạo AverageUnitPriceFormController
    private static AverageUnitPriceFormController createAverageUnitPriceController(BookRepository repository) {
        AverageUnitPriceResultForm averageUnitPriceResultForm = new AverageUnitPriceResultForm();
        AverageUnitPricePresenter averageUnitPricePresenter = new AverageUnitPricePresenter(averageUnitPriceResultForm);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(repository, averageUnitPricePresenter);
        return new AverageUnitPriceFormController(averageUnitPriceService);
    }

    // Phương thức tạo ExportBookFormController
    private static ExportBookFormController createExportBookController(BookRepository repository) {
        // Khởi tạo các thành phần liên quan đến xuất sách
        ExportBookResultForm exportBookResultForm = new ExportBookResultForm();
        ExportBookPresenter exportBookPresenter = new ExportBookPresenter(exportBookResultForm);
        ExportBookService exportBookService = new ExportBookService(repository, exportBookPresenter);
        return new ExportBookFormController(exportBookService);
    }
}
