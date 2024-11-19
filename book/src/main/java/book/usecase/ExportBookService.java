package book.usecase;

import java.util.List;

import book.database.BookRepository;
import book.entities.Book;
import book.entities.TextBook;
import book.exportbook.ExportBookInputBoundary;
import book.exportbook.ExportBookOutputBoundary;
import book.exportbook.ExportBookRequestData;
import book.exportbook.ExportBookResponseData;

import java.util.stream.Collectors;

public class ExportBookService implements ExportBookInputBoundary {
    private BookRepository bookRepository;
    private ExportBookOutputBoundary exportBookPresenter;

    public ExportBookService(BookRepository bookRepository, ExportBookOutputBoundary exportBookPresenter) {
        this.bookRepository = bookRepository;
        this.exportBookPresenter = exportBookPresenter;
    }

    @Override
    public void exportBook(ExportBookRequestData requestData) {
        // Lấy tất cả sách từ repository
        List<Book> allBooks = bookRepository.getAllBooks();
        
        // Lọc ra những sách là TextBook và có publisher khớp với yêu cầu
        List<Book> filteredBooks = allBooks.stream()
            .filter(book -> book instanceof TextBook && book.getPublisher().equals(requestData.getPublisher()))
            .collect(Collectors.toList());

        // Tạo đối tượng ExportBookResponseData từ danh sách sách lọc
        ExportBookResponseData responseData = new ExportBookResponseData(filteredBooks);
        exportBookPresenter.presentExportBookResult(responseData);
    }
}

