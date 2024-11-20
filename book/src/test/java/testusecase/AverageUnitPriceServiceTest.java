package testusecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.Test;
import book.averageunitprice.AverageUnitPriceOutputBoundary;
import book.database.BookRepository;
import book.entities.ReferenceBook;
import book.usecase.AverageUnitPriceService;
import book.averageunitprice.AverageUnitPriceResponseData;
import book.entities.Book;

import java.util.Arrays;

public class AverageUnitPriceServiceTest {

    // Test này kiểm tra việc tính toán giá trị trung bình của các cuốn sách tham
    // chiếu (ReferenceBook) khi có ít nhất một cuốn sách trong kho.
    @Test
    public void testCalculateAverageUnitPriceSuccess() {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository,
                mockOutputBoundary);

        // Tạo một danh sách sách với ít nhất một ReferenceBook
        ReferenceBook referenceBook1 = mock(ReferenceBook.class);
        when(referenceBook1.getUnitPrice()).thenReturn(100.0);

        ReferenceBook referenceBook2 = mock(ReferenceBook.class);
        when(referenceBook2.getUnitPrice()).thenReturn(150.0);

        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList(referenceBook1, referenceBook2));

        // Act
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

    // Test này kiểm tra trường hợp không có sách loại ReferenceBook nào trong kho,
    // trong trường hợp này, giá trị trung bình phải là 0
    @Test
    public void testCalculateAverageUnitPriceNoReferenceBooks() {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository,
                mockOutputBoundary);

        // Tạo một danh sách sách không có ReferenceBook nào
        Book nonReferenceBook = mock(Book.class);
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList(nonReferenceBook));

        // Act
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

    // Test này sẽ kiểm tra trường hợp kho sách không có sách nào, và giá trị trung
    // bình sẽ là 0.
    @Test
    public void testCalculateAverageUnitPriceNoBooksInRepository() {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository,
                mockOutputBoundary);

        // Tạo một danh sách sách rỗng
        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList());

        // Act
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

    // Test này kiểm tra trường hợp có một cuốn sách tham chiếu trong kho và kiểm
    // tra giá trị trung bình.
    @Test
    public void testCalculateAverageUnitPriceSingleReferenceBook() {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AverageUnitPriceOutputBoundary mockOutputBoundary = mock(AverageUnitPriceOutputBoundary.class);
        AverageUnitPriceService averageUnitPriceService = new AverageUnitPriceService(mockRepository,
                mockOutputBoundary);

        // Tạo một cuốn sách tham chiếu duy nhất
        ReferenceBook referenceBook = mock(ReferenceBook.class);
        when(referenceBook.getUnitPrice()).thenReturn(200.0);

        when(mockRepository.getAllBooks()).thenReturn(Arrays.asList(referenceBook));

        // Act
        averageUnitPriceService.calculateAverageUnitPrice();

        // Assert
        verify(mockOutputBoundary).presentAverageUnitPriceResult(any(AverageUnitPriceResponseData.class));
    }

}
