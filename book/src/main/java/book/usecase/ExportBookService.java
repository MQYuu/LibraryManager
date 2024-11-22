package book.usecase;

import java.util.List;

import book.database.BookDBBoundary;
import book.entities.Book;
import book.exportbook.ExportBookInputBoundary;
import book.exportbook.ExportBookOutputBoundary;
import book.exportbook.ExportBookRequestData;
import book.exportbook.ExportBookResponseData;

import java.util.stream.Collectors;

public class ExportBookService implements ExportBookInputBoundary {
    private BookDBBoundary bookRepository;
    private ExportBookOutputBoundary exportBookOutputBoundary;

    public ExportBookService(BookDBBoundary bookRepository, ExportBookOutputBoundary exportBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.exportBookOutputBoundary = exportBookOutputBoundary;
    }

    @Override
    public void exportBook(ExportBookRequestData requestData) {
        // Lấy tất cả sách từ repository
        List<Book> allBooks = bookRepository.getAllBooks();
        
        // Kiểm tra xem danh sách sách có dữ liệu không
        if (allBooks.isEmpty()) {
            System.out.println("No books found in the repository.");
        } else {
            System.out.println("Total books in repository: " + allBooks.size());
        }
    
        // Lọc ra tất cả sách có publisher khớp với yêu cầu (bao gồm cả TextBook và ReferenceBook)
        List<Book> filteredBooks = allBooks.stream()
            .filter(book -> book.getPublisher().equalsIgnoreCase(requestData.getPublisher()))
            .collect(Collectors.toList());
        
        // Kiểm tra kết quả lọc
        if (filteredBooks.isEmpty()) {
            System.out.println("No books found for publisher: " + requestData.getPublisher());
        } else {
            System.out.println("Found " + filteredBooks.size() + " books for publisher: " + requestData.getPublisher());
        }
    
        // Tạo đối tượng ExportBookResponseData từ danh sách sách lọc
        ExportBookResponseData responseData = new ExportBookResponseData(filteredBooks);
        exportBookOutputBoundary.presentExportBookResult(responseData);
    }
    
    
}

