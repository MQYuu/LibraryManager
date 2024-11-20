package testusecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;

import book.database.BookRepository;
import book.exportbook.ExportBookOutputBoundary;
import book.exportbook.ExportBookRequestData;
import book.exportbook.ExportBookResponseData;
import book.usecase.ExportBookService;
import book.entities.ReferenceBook;
import book.entities.Book;

import java.util.Arrays;
import java.util.List;

public class ExportBookServiceTest {
    //Test kiểm tra xem có xuất ra thông tin sách thành công hay không
    @Test
    public void testExportBooksSuccess() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        ExportBookOutputBoundary mockOutputBoundary = mock(ExportBookOutputBoundary.class);
        ExportBookService exportBookService = new ExportBookService(mockRepository, mockOutputBoundary);

        // Tạo danh sách sách giả lập
        List<Book> mockBooks = Arrays.asList(
            new ReferenceBook("R001", "2024-11-20", 100.0, 10, "Publisher1", 5.0),
            new ReferenceBook("R002", "2024-11-20", 150.0, 12, "Publisher1", 7.0)
        );
        
        // Giả lập hành động lấy sách từ repository
        when(mockRepository.getAllBooks()).thenReturn(mockBooks);

        // Dữ liệu yêu cầu xuất sách
        ExportBookRequestData requestData = new ExportBookRequestData("Publisher1");

        // Act: Gọi phương thức xuất sách
        exportBookService.exportBook(requestData);

        // Assert: Kiểm tra xem phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks(); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentExportBookResult(any(ExportBookResponseData.class)); 
    }
    
    //Test này kiểm tra xem có báo lỗi khi xóa sách không tồn tại hay không
    @Test
    public void testExportBooksWithNoMatchingPublisher() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        ExportBookOutputBoundary mockOutputBoundary = mock(ExportBookOutputBoundary.class);
        ExportBookService exportBookService = new ExportBookService(mockRepository, mockOutputBoundary);

        // Tạo danh sách sách giả lập
        List<Book> mockBooks = Arrays.asList(
            new ReferenceBook("R001", "2024-11-20", 100.0, 10, "Publisher1", 5.0),
            new ReferenceBook("R002", "2024-11-20", 150.0, 12, "Publisher1", 7.0)
        );

        // Giả lập hành động lấy sách từ repository
        when(mockRepository.getAllBooks()).thenReturn(mockBooks);

        // Dữ liệu yêu cầu xuất sách cho publisher không có trong danh sách
        ExportBookRequestData requestData = new ExportBookRequestData("Publisher2");

        // Act: Gọi phương thức xuất sách
        exportBookService.exportBook(requestData);

        // Assert: Kiểm tra xem phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks(); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentExportBookResult(any(ExportBookResponseData.class)); 
    }
}
