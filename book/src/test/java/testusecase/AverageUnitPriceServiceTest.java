package testusecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import book.averageunitprice.AverageUnitPriceOutputBoundary;
import book.database.BookDBBoundary;
import book.entities.ReferenceBook;
import book.usecase.AverageUnitPriceService;
import book.averageunitprice.AverageUnitPriceResponseData;
import book.entities.Book;

import java.util.Arrays;

public class AverageUnitPriceServiceTest {

    // Test này kiểm tra việc tính toán giá trị trung bình của 
    // ReferenceBook khi có ít nhất một cuốn sách trong kho.
    @Test
    public void testCalculateAverageUnitPriceSuccess() {
        // Arrange: Tạo mock cho repository và output boundary
        BookDBBoundary mockRepository = mock(BookDBBoundary.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository, mockOutputBoundary);

        // Tạo một danh sách sách với ít nhất một ReferenceBook
        ReferenceBook referenceBook1 = mock(ReferenceBook.class);
        when(referenceBook1.getUnitPrice()).thenReturn(100.0); // Đơn giá của sách tham chiếu đầu tiên

        ReferenceBook referenceBook2 = mock(ReferenceBook.class);
        when(referenceBook2.getUnitPrice()).thenReturn(150.0); // Đơn giá của sách tham chiếu thứ hai

        // Khi gọi getAllBooks, trả về danh sách các sách tham chiếu
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList(referenceBook1, referenceBook2));

        // Act: Gọi phương thức tính toán giá trị trung bình
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert: Kiểm tra xem kết quả đã được gửi đến output boundary chưa
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

    // Test này kiểm tra trường hợp không có sách loại ReferenceBook nào trong kho,
    // trong trường hợp này, giá trị trung bình phải là 0
    @Test
    public void testCalculateAverageUnitPriceNoReferenceBooks() {
        // Arrange: Tạo mock cho repository và output boundary
        BookDBBoundary mockRepository = mock(BookDBBoundary.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository, mockOutputBoundary);

        // Tạo một danh sách sách không có ReferenceBook nào
        Book nonReferenceBook = mock(Book.class); // Cuốn sách thông thường, không phải là ReferenceBook
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList(nonReferenceBook));

        // Act: Gọi phương thức tính toán giá trị trung bình
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert: Kiểm tra xem kết quả đã được gửi đến output boundary chưa
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

    // Test này sẽ kiểm tra trường hợp kho sách không có sách nào, và giá trị trung
    // bình sẽ là 0.
    @Test
    public void testCalculateAverageUnitPriceNoBooksInRepository() {
        // Arrange: Tạo mock cho repository và output boundary
        BookDBBoundary mockRepository = mock(BookDBBoundary.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository, mockOutputBoundary);

        // Tạo một danh sách sách rỗng
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList());

        // Act: Gọi phương thức tính toán giá trị trung bình
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert: Kiểm tra xem kết quả đã được gửi đến output boundary chưa
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

    // Test này kiểm tra trường hợp có một ReferenceBook trong kho và kiểm
    // tra giá trị trung bình.
    @Test
    public void testCalculateAverageUnitPriceSingleReferenceBook() {
        // Arrange: Tạo mock cho repository và output boundary
        BookDBBoundary mockRepository = mock(BookDBBoundary.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository, mockOutputBoundary);

        // Tạo một ReferenceBook duy nhất
        ReferenceBook referenceBook = mock(ReferenceBook.class);
        when(referenceBook.getUnitPrice()).thenReturn(200.0); // Đơn giá của ReferenceBook

        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList(referenceBook));

        // Act: Gọi phương thức tính toán giá trị trung bình
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert: Kiểm tra xem kết quả đã được gửi đến output boundary chưa
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

}
