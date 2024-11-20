package testusecase;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import book.database.BookRepository;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.getbooklist.GetBookListOutputBoundary;
import book.getbooklist.GetBookListResponseData;
import book.usecase.GetBookListService;
import static org.junit.Assert.assertEquals;
import org.mockito.ArgumentCaptor;

public class GetBookListServiceTest {
    // Test kiểm tra xem có lấy được thông tin sách hay không
    @Test
    public void testGetBookList() {
        // Arrange: Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        GetBookListOutputBoundary mockOutputBoundary = mock(GetBookListOutputBoundary.class);

        // Tạo các sách giả lập
        Book book1 = new ReferenceBook("R001", "2024-01-01", 200.0, 5, "Publisher A", 15.0);
        Book book2 = new TextBook("T001", "2024-01-02", 100.0, 10, "Publisher B", "new");
        Book book3 = new TextBook("T002", "2024-01-03", 50.0, 8, "Publisher C", "used");

        // Danh sách sách giả lập
        List<Book> mockBookList = Arrays.asList(book1, book2, book3);

        // Giả lập hành động lấy sách từ repository
        when(mockRepository.getAllBooks()).thenReturn(mockBookList);

        // Tạo đối tượng dịch vụ
        GetBookListService getBookListService = new GetBookListService(mockRepository, mockOutputBoundary);

        // Act: Gọi phương thức lấy danh sách sách
        getBookListService.getBookList();

        // Assert: Kiểm tra xem phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Sử dụng ArgumentCaptor để chụp lại tham số đầu vào của presentBookList
        ArgumentCaptor<GetBookListResponseData> captor = ArgumentCaptor.forClass(GetBookListResponseData.class);
        verify(mockOutputBoundary).presentBookList(captor.capture());

        // Kiểm tra xem danh sách sách trong responseData có khớp với danh sách mong đợi không
        assertEquals(mockBookList, captor.getValue().getBookList());
    }
}
