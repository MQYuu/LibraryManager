package testusecase;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import book.database.BookRepository;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.printbooklist.PrintBookOutputBoundary;
import book.usecase.PrintBookService;

public class PrintBookServiceTest {

    // Test trường hợp có sách hợp lệ trong repository
    @Test
    public void testPrintBookWithValidBooks() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        PrintBookOutputBoundary mockOutputBoundary = mock(PrintBookOutputBoundary.class);
        PrintBookService printBookService = new PrintBookService(mockRepository, mockOutputBoundary);

        // Tạo sách giả lập
        Book book1 = new ReferenceBook("R001", "2024-01-01", 200.0, 5, "Publisher A", 15.0);
        Book book2 = new TextBook("T001", "2024-01-02", 100.0, 10, "Publisher B", "new");

        // Giả lập repository trả về danh sách sách
        List<Book> mockBookList = Arrays.asList(book1, book2);
        when(mockRepository.getAllBooks()).thenReturn(mockBookList);

        // Act: Gọi phương thức printBook
        printBookService.printBook();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Kiểm tra phương thức presentPrintBookResult đã được gọi với dữ liệu đúng
        verify(mockOutputBoundary).presentPrintBookResult(anyList());
    }

    // Test trường hợp repository trả về danh sách sách rỗng
    @Test
    public void testPrintBookWithEmptyBooks() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        PrintBookOutputBoundary mockOutputBoundary = mock(PrintBookOutputBoundary.class);
        PrintBookService printBookService = new PrintBookService(mockRepository, mockOutputBoundary);

        // Giả lập repository trả về danh sách sách trống
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList());

        // Act: Gọi phương thức printBook
        printBookService.printBook();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Kiểm tra phương thức presentPrintBookResult đã được gọi với danh sách rỗng
        verify(mockOutputBoundary).presentPrintBookResult(Arrays.asList());
    }

    // Test trường hợp repository trả về null
    @Test
    public void testPrintBookWithNullBooks() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        PrintBookOutputBoundary mockOutputBoundary = mock(PrintBookOutputBoundary.class);
        PrintBookService printBookService = new PrintBookService(mockRepository, mockOutputBoundary);

        // Giả lập repository trả về null
        when(mockRepository.getAllBooks()).thenReturn(null);

        // Act: Gọi phương thức printBook
        printBookService.printBook();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Kiểm tra phương thức presentPrintBookResult đã được gọi với danh sách trống
        verify(mockOutputBoundary).presentPrintBookResult(Arrays.asList());
    }

    // Test trường hợp danh sách sách có đối tượng null
    @Test
    public void testPrintBookWithNullBookInList() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        PrintBookOutputBoundary mockOutputBoundary = mock(PrintBookOutputBoundary.class);
        PrintBookService printBookService = new PrintBookService(mockRepository, mockOutputBoundary);

        // Tạo sách giả lập với một đối tượng sách null
        Book book1 = new ReferenceBook("R001", "2024-01-01", 200.0, 5, "Publisher A", 15.0);
        Book book2 = null;  // Sách null
        List<Book> mockBookList = Arrays.asList(book1, book2);

        // Giả lập repository trả về danh sách sách chứa null
        when(mockRepository.getAllBooks()).thenReturn(mockBookList);

        // Act: Gọi phương thức printBook
        printBookService.printBook();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Kiểm tra phương thức presentPrintBookResult đã được gọi
        verify(mockOutputBoundary).presentPrintBookResult(anyList());
    }
}
