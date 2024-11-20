package testusecase;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import book.database.BookRepository;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.totalbookprice.TotalBookPriceOutputBoundary;
import book.totalbookprice.TotalBookPriceResponseData;
import book.usecase.TotalBookPriceService;
import org.mockito.ArgumentCaptor;

public class TotalBookPriceServiceTest {

    // Test trường hợp có sách hợp lệ trong repository
    @Test
    public void testCalculateTotalBookPriceWithValidBooks() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        TotalBookPriceOutputBoundary mockOutputBoundary = mock(TotalBookPriceOutputBoundary.class);
        TotalBookPriceService totalBookPriceService = new TotalBookPriceService(mockRepository, mockOutputBoundary);

        // Tạo sách giả lập với phương thức calculateTotalPrice() trả về giá trị hợp lệ
        Book book1 = mock(ReferenceBook.class);
        when(book1.calculateTotalPrice()).thenReturn(200.0);

        Book book2 = mock(TextBook.class);
        when(book2.calculateTotalPrice()).thenReturn(100.0);

        List<Book> mockBookList = Arrays.asList(book1, book2);
        when(mockRepository.getAllBooks()).thenReturn(mockBookList);

        // Act: Gọi phương thức calculateTotalBookPrice
        totalBookPriceService.calculateTotalBookPrice();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Kiểm tra phương thức calculateTotalPrice() đã được gọi cho từng sách
        verify(book1).calculateTotalPrice();
        verify(book2).calculateTotalPrice();

        // Sử dụng ArgumentCaptor để nắm bắt đối tượng được truyền vào
        ArgumentCaptor<TotalBookPriceResponseData> captor = ArgumentCaptor.forClass(TotalBookPriceResponseData.class);
        verify(mockOutputBoundary).presentTotalBookPrice(captor.capture());

        // Kiểm tra tổng giá trị của sách được tính toán chính xác
        TotalBookPriceResponseData capturedResponse = captor.getValue();
        assert capturedResponse != null; // Đảm bảo không phải null
        assert capturedResponse.getTotalPrice() == 300.0; // Kiểm tra giá trị tổng hợp đúng
    }

    // Test trường hợp repository trả về danh sách sách trống
    @Test
    public void testCalculateTotalBookPriceWithEmptyBooks() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        TotalBookPriceOutputBoundary mockOutputBoundary = mock(TotalBookPriceOutputBoundary.class);
        TotalBookPriceService totalBookPriceService = new TotalBookPriceService(mockRepository, mockOutputBoundary);

        // Giả lập repository trả về danh sách sách trống
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList());

        // Act: Gọi phương thức calculateTotalBookPrice
        totalBookPriceService.calculateTotalBookPrice();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Sử dụng ArgumentCaptor để nắm bắt đối tượng được truyền vào
        ArgumentCaptor<TotalBookPriceResponseData> captor = ArgumentCaptor.forClass(TotalBookPriceResponseData.class);
        verify(mockOutputBoundary).presentTotalBookPrice(captor.capture());

        // Kiểm tra tổng giá trị khi không có sách là 0
        TotalBookPriceResponseData capturedResponse = captor.getValue();
        assert capturedResponse != null; // Đảm bảo không phải null
        assert capturedResponse.getTotalPrice() == 0.0; // Kiểm tra tổng giá trị bằng 0
    }

    // Test trường hợp repository trả về null
    @Test
    public void testCalculateTotalBookPriceWithNullBooks() {
        // Arrange: Mock repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        TotalBookPriceOutputBoundary mockOutputBoundary = mock(TotalBookPriceOutputBoundary.class);
        TotalBookPriceService totalBookPriceService = new TotalBookPriceService(mockRepository, mockOutputBoundary);

        // Giả lập repository trả về null và xử lý bằng cách trả về danh sách trống trong Service
        when(mockRepository.getAllBooks()).thenReturn(null);

        // Act: Gọi phương thức calculateTotalBookPrice
        totalBookPriceService.calculateTotalBookPrice();

        // Assert: Kiểm tra phương thức getAllBooks đã được gọi
        verify(mockRepository).getAllBooks();

        // Sử dụng ArgumentCaptor để nắm bắt đối tượng được truyền vào
        ArgumentCaptor<TotalBookPriceResponseData> captor = ArgumentCaptor.forClass(TotalBookPriceResponseData.class);
        verify(mockOutputBoundary).presentTotalBookPrice(captor.capture());

        // Kiểm tra tổng giá trị khi không có sách là 0
        TotalBookPriceResponseData capturedResponse = captor.getValue();
        assert capturedResponse != null; // Đảm bảo không phải null
        assert capturedResponse.getTotalPrice() == 0.0; // Kiểm tra tổng giá trị bằng 0
    }
}
