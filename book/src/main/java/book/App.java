package book;

import book.database.BookMySQL;
import book.database.BookDBBoundary;

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
import book.ui.totalbookprice.TotalBookPriceFormController;
import book.usecase.TotalBookPriceService;
import book.ui.totalbookprice.TotalBookPriceResultForm;

import book.ui.averageunitprice.AverageUnitPriceFormController;
import book.ui.averageunitprice.AverageUnitPriceResultForm;
import book.usecase.AverageUnitPriceService;
import book.averageunitprice.AverageUnitPricePresenter;

import book.ui.exportbook.ExportBookFormController;
import book.usecase.ExportBookService;
import book.exportbook.ExportBookPresenter;
import book.ui.exportbook.ExportBookResultForm;

public class App {
    public static void main(String[] args) {
        // Khởi tạo các thành phần cần thiết
        BookDBBoundary db = new BookMySQL(); // Khởi tạo BookMySQL trực tiếp như BookDBBoundary

        // Khởi tạo các controller và dịch vụ với db
        AddBookFormController addBookFormController = createAddBookController(db);
        EditBookFormController editBookFormController = createEditBookController(db);
        DeleteBookFormController deleteBookFormController = createDeleteBookController(db);
        SearchBookFormController searchBookFormController = createSearchBookController(db);
        PrintBookFormController printBookFormController = createPrintBookController(db);
        TotalBookPriceFormController totalBookPriceFormController = createTotalBookPriceController(db);
        AverageUnitPriceFormController averageUnitPriceFormController = createAverageUnitPriceController(db);
        ExportBookFormController exportBookFormController = createExportBookController(db);

        // Tạo MainForm và liên kết với các controller
        MainForm mainForm = new MainForm(
                addBookFormController, 
                editBookFormController, 
                deleteBookFormController,
                searchBookFormController, 
                printBookFormController,
                totalBookPriceFormController, 
                averageUnitPriceFormController, 
                exportBookFormController
        );

        // Tạo và khởi tạo GetBookListService
        GetBookListPresenter getBookListPresenter = new GetBookListPresenter(mainForm);
        GetBookListService getBookListService = new GetBookListService(db, getBookListPresenter);

        // Khởi tạo MainFormController
        MainFormController mainFormController = new MainFormController(getBookListService);

        // Gọi loadBooks để tải sách 
        mainFormController.loadBooks();
        // Hiển thị MainForm
        mainForm.setVisible(true);
    }

    // Các phương thức tạo controller

    private static AddBookFormController createAddBookController(BookDBBoundary db) {
        AddBookResultForm resultForm = new AddBookResultForm();
        AddBookPresenter addBookPresenter = new AddBookPresenter(resultForm);
        AddBookService addBookService = new AddBookService(db, addBookPresenter);
        return new AddBookFormController(addBookService);
    }

    private static EditBookFormController createEditBookController(BookDBBoundary db) {
        EditBookPresenter editBookPresenter = new EditBookPresenter();
        EditBookService editBookService = new EditBookService(db, editBookPresenter);
        return new EditBookFormController(editBookService);
    }

    private static DeleteBookFormController createDeleteBookController(BookDBBoundary db) {
        DeleteBookPresenter deleteBookPresenter = new DeleteBookPresenter();
        DeleteBookService deleteBookService = new DeleteBookService(db, deleteBookPresenter);
        return new DeleteBookFormController(deleteBookService);
    }

    private static SearchBookFormController createSearchBookController(BookDBBoundary db) {
        SearchBookPresenter searchBookPresenter = new SearchBookPresenter();
        SearchBookService searchBookService = new SearchBookService(db, searchBookPresenter);
        return new SearchBookFormController(searchBookService);
    }

    private static PrintBookFormController createPrintBookController(BookDBBoundary db) {
        PrintBookResultForm printBookResultForm = new PrintBookResultForm();
        PrintBookOutputBoundary printBookOutputBoundary = new PrintBookPresenter(printBookResultForm);
        PrintBookListInputBoundary printBookService = new PrintBookService(db, printBookOutputBoundary);
        return new PrintBookFormController(printBookService);
    }

    private static TotalBookPriceFormController createTotalBookPriceController(BookDBBoundary db) {
        TotalBookPriceResultForm totalBookPriceResultForm = new TotalBookPriceResultForm();
        TotalBookPriceOutputBoundary totalBookPriceOutputBoundary = new TotalBookPricePresenter(totalBookPriceResultForm);
        TotalBookPriceInputBoundary totalBookPriceService = new TotalBookPriceService(db, totalBookPriceOutputBoundary);
        return new TotalBookPriceFormController(totalBookPriceService);
    }

    private static AverageUnitPriceFormController createAverageUnitPriceController(BookDBBoundary db) {
        AverageUnitPriceResultForm averageUnitPriceResultForm = new AverageUnitPriceResultForm();
        AverageUnitPricePresenter averageUnitPricePresenter = new AverageUnitPricePresenter(averageUnitPriceResultForm);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(db, averageUnitPricePresenter);
        return new AverageUnitPriceFormController(averageUnitPriceService);
    }

    private static ExportBookFormController createExportBookController(BookDBBoundary db) {
        ExportBookResultForm exportBookResultForm = new ExportBookResultForm();
        ExportBookPresenter exportBookPresenter = new ExportBookPresenter(exportBookResultForm);
        ExportBookService exportBookService = new ExportBookService(db, exportBookPresenter);
        return new ExportBookFormController(exportBookService);
    }
}
