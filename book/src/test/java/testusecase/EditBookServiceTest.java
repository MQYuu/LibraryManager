package testusecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;

import book.database.BookRepository;
import book.editbook.EditBookOutputBoundary;
import book.editbook.EditBookRequestData;
import book.editbook.EditBookResponseData;
import book.usecase.EditBookService;

public class EditBookServiceTest {
    // Test này kiểm tra khi sách được cập nhật thành công trong kho.
    @Test
    public void testEditBookSuccess() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        EditBookOutputBoundary mockOutputBoundary = mock(EditBookOutputBoundary.class);
        EditBookService editBookService = new EditBookService(mockRepository, mockOutputBoundary);

        // Dữ liệu yêu cầu chỉnh sửa sách
        EditBookRequestData requestData = new EditBookRequestData("T001", "2024-11-20", 100.0, 10, "Publisher A", "New",
                10.0);

        // Giả lập hành động chỉnh sửa sách thành công
        doNothing().when(mockRepository).editBook(requestData);

        // Act: Gọi phương thức chỉnh sửa sách
        editBookService.editBook(requestData);

        // Assert: Kiểm tra xem phương thức chỉnh sửa có được gọi không
        verify(mockRepository).editBook(requestData); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentEditBookResult(any(EditBookResponseData.class)); 
    }

    // Test này kiểm tra khi không thể chỉnh sửa sách vì lý do nào đó, chẳng hạn như
    // không tìm thấy sách hoặc có lỗi trong quá trình chỉnh sửa.
    @Test
    public void testEditBookFailure() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        EditBookOutputBoundary mockOutputBoundary = mock(EditBookOutputBoundary.class);
        EditBookService editBookService = new EditBookService(mockRepository, mockOutputBoundary);

        // Dữ liệu yêu cầu chỉnh sửa sách
        EditBookRequestData requestData = new EditBookRequestData("T001", "2024-11-20", 100.0, 10, "Publisher A", "New",
                10.0);

        // Giả lập hành động chỉnh sửa sách thất bại, ném ra ngoại lệ
        doThrow(new RuntimeException("Error updating book")).when(mockRepository).editBook(requestData);

        // Act: Gọi phương thức chỉnh sửa sách
        editBookService.editBook(requestData);

        // Assert: Kiểm tra xem phương thức chỉnh sửa có được gọi không
        verify(mockRepository).editBook(requestData); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentEditBookResult(any(EditBookResponseData.class)); 
    }

    // Test này kiểm tra khi thông tin truyền vào không hợp lệ (ví dụ: thiếu trường
    // thông tin quan trọng).
    @Test
    public void testEditBookWithInvalidData() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        EditBookOutputBoundary mockOutputBoundary = mock(EditBookOutputBoundary.class);
        EditBookService editBookService = new EditBookService(mockRepository, mockOutputBoundary);

        // Dữ liệu yêu cầu chỉnh sửa sách với ID rỗng (không hợp lệ)
        EditBookRequestData requestData = new EditBookRequestData("", "2024-11-20", 100.0, 10, "Publisher A", "New",
                10.0);

        // Act: Gọi phương thức chỉnh sửa sách
        editBookService.editBook(requestData);

        // Assert: Kiểm tra xem phương thức chỉnh sửa không được gọi nếu dữ liệu không hợp lệ
        verify(mockRepository, never()).editBook(any(EditBookRequestData.class)); 

        // Kiểm tra xem kết quả đã được trình bày qua output boundary chưa
        verify(mockOutputBoundary).presentEditBookResult(any(EditBookResponseData.class)); 
    }

}
