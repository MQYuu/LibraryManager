package testusecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import book.database.BookRepository;
import book.deletebook.DeleteBookOutputBoundary;
import book.deletebook.DeleteBookRequestData;
import book.deletebook.DeleteBookResponseData;
import book.usecase.DeleteBookService;

public class DeleteBookServiceTest {
    // Test này kiểm tra xem sách có được xóa thành công hay không
    @Test
    public void testDeleteBookSuccess() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        DeleteBookOutputBoundary mockOutputBoundary = mock(DeleteBookOutputBoundary.class);
        DeleteBookService deleteBookService = new DeleteBookService(mockRepository, mockOutputBoundary);

        // Giả lập hành động xóa sách thành công
        when(mockRepository.deleteBook("T001")).thenReturn(true);

        // Dữ liệu yêu cầu xóa sách
        DeleteBookRequestData requestData = new DeleteBookRequestData("T001");

        // Act: Gọi phương thức xóa sách
        deleteBookService.deleteBook(requestData);

        // Assert: Kiểm tra xem phương thức xóa sách có được gọi không
        verify(mockRepository).deleteBook("T001"); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentDeleteBookResult(any(DeleteBookResponseData.class)); 
    }

    // Test này kiểm tra khi xóa sách không thành công.
    @Test
    public void testDeleteBookFailure() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        DeleteBookOutputBoundary mockOutputBoundary = mock(DeleteBookOutputBoundary.class);
        DeleteBookService deleteBookService = new DeleteBookService(mockRepository, mockOutputBoundary);

        // Giả lập hành động xóa sách không thành công
        when(mockRepository.deleteBook("T001")).thenReturn(false);

        // Dữ liệu yêu cầu xóa sách
        DeleteBookRequestData requestData = new DeleteBookRequestData("T001");

        // Act: Gọi phương thức xóa sách
        deleteBookService.deleteBook(requestData);

        // Assert: Kiểm tra xem phương thức xóa sách có được gọi không
        verify(mockRepository).deleteBook("T001"); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentDeleteBookResult(any(DeleteBookResponseData.class)); 
    }

    // Test này kiểm tra khi cố gắng xóa một cuốn sách không tồn tại trong kho.
    @Test
    public void testDeleteBookNotFound() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        DeleteBookOutputBoundary mockOutputBoundary = mock(DeleteBookOutputBoundary.class);
        DeleteBookService deleteBookService = new DeleteBookService(mockRepository, mockOutputBoundary);

        // Giả lập rằng không tìm thấy sách với ID "T001"
        when(mockRepository.deleteBook("T001")).thenReturn(false);

        // Dữ liệu yêu cầu xóa sách
        DeleteBookRequestData requestData = new DeleteBookRequestData("T001");

        // Act: Gọi phương thức xóa sách
        deleteBookService.deleteBook(requestData);

        // Assert: Kiểm tra xem phương thức xóa sách có được gọi không
        verify(mockRepository).deleteBook("T001"); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentDeleteBookResult(any(DeleteBookResponseData.class)); 
    }
}
