package testusecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import book.addbook.AddBookOutputBoundary;
import book.addbook.AddBookRequestData;
import book.addbook.AddBookResponseData;
import book.database.BookRepository;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.usecase.AddBookService;

public class TestAddBookUsecase {

    // Hàm trợ giúp để tạo ngày từ chuỗi
    private Date createDateFromString(String dateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    }

    // Kiểm tra xem dịch vụ có thể thêm một textbook vào repository và trả về thông
    // báo thành công hay không
    @Test
    public void testAddTextBookSuccess() throws Exception {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockRepository, mockOutputBoundary);

        // Thêm thông tin cho TextBook, với condition "New"
        AddBookRequestData requestData = new AddBookRequestData(
                "T001",
                createDateFromString("2024-11-20").toString(), // Chuyển đổi Date thành String
                100.0,
                10,
                "Publisher A",
                "TextBook",
                "New",
                0.0);

        // Act
        addBookService.executeAdd(requestData);

        // Assert
        verify(mockRepository).addBook(any(TextBook.class)); // Kiểm tra xem sách có được thêm vào repository hay không
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class)); // Kiểm tra xem kết quả được trình bày
                                                                                  // đúng hay không
    }

    // Kiểm tra xem dịch vụ có thể thêm một reference book vào repository và trả về
    // thông báo thành công hay không
    @Test
    public void testAddReferenceBookSuccess() throws Exception {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockRepository, mockOutputBoundary);

        // Thêm thông tin cho ReferenceBook, với tax 20.0
        AddBookRequestData requestData = new AddBookRequestData(
                "R001",
                createDateFromString("2024-11-20").toString(), // Chuyển đổi Date thành String
                150.0,
                5,
                "Publisher B",
                "ReferenceBook",
                null,
                20.0);

        // Act
        addBookService.executeAdd(requestData);

        // Assert
        verify(mockRepository).addBook(any(ReferenceBook.class)); // Kiểm tra xem sách có được thêm vào repository hay
                                                                  // không
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class)); // Kiểm tra xem kết quả được trình bày
                                                                                  // đúng hay không
    }

    // Kiểm tra xem có xảy ra lỗi khi không thể thêm sách vào repository.
    @Test
    public void testAddBookWithInvalidData() throws Exception {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockRepository, mockOutputBoundary);

        // Dữ liệu thiếu thông tin quan trọng (ví dụ: thiếu bookId)
        AddBookRequestData requestData = new AddBookRequestData(
                null,
                createDateFromString("2024-11-20").toString(), // Chuyển đổi Date thành String
                100.0,
                10,
                "Publisher A",
                "TextBook",
                "New",
                0.0);

        // Act
        addBookService.executeAdd(requestData);

        // Assert
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class)); // Kiểm tra xem kết quả được trình bày
        // Có thể kiểm tra thông báo lỗi trong AddBookResponseData nếu cần thiết
    }

    // kiểm tra dữ liệu trống hoặc không hợp lệ khi thêm referencebook
    @Test
    public void testAddReferenceBookWithInvalidData() throws Exception {
        // Arrange
        BookRepository mockRepository = mock(BookRepository.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockRepository, mockOutputBoundary);

        // Dữ liệu không hợp lệ (ví dụ: thiếu tax cho reference book)
        AddBookRequestData requestData = new AddBookRequestData(
                "R001",
                createDateFromString("2024-11-20").toString(), // Chuyển đổi Date thành String
                150.0,
                5,
                "Publisher B",
                "ReferenceBook",
                null,
                -5.0 // tax không hợp lệ
        );

        // Act
        addBookService.executeAdd(requestData);

        // Assert
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class)); // Kiểm tra kết quả
    }
}
