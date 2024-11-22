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
import book.database.BookDBBoundary;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.usecase.AddBookService;

public class AddBookUsecaseTest {

    // Hàm trợ giúp: Chuyển đổi chuỗi ngày thành đối tượng Date
    private Date createDateFromString(String dateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    }

    // Test: Thêm sách TextBook hợp lệ
    @Test
    public void testAddTextBookSuccess() throws Exception {
        // Arrange: Tạo mock cho BookDBBoundary và output boundary
        BookDBBoundary mockDBBoundary = mock(BookDBBoundary.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockDBBoundary, mockOutputBoundary);

        // Dữ liệu cho TextBook 
        AddBookRequestData requestData = new AddBookRequestData(
                "T001",
                createDateFromString("2024-11-20").toString(), 
                100.0, 
                10, 
                "Publisher A",
                "TextBook", 
                "New", // Tình trạng
                0.0 // Tax (không dùng cho TextBook)
        );

        // Act: Gọi phương thức thêm sách
        addBookService.executeAdd(requestData);

        // Assert: Kiểm tra sách được thêm vào database boundary
        verify(mockDBBoundary).saveBook(any(TextBook.class));
        // Kiểm tra kết quả được gửi tới output boundary
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class));
    }

    // Test: Thêm sách ReferenceBook hợp lệ
    @Test
    public void testAddReferenceBookSuccess() throws Exception {
        // Arrange: Tạo mock cho BookDBBoundary và output boundary
        BookDBBoundary mockDBBoundary = mock(BookDBBoundary.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockDBBoundary, mockOutputBoundary);

        // Dữ liệu cho ReferenceBook 
        AddBookRequestData requestData = new AddBookRequestData(
                "R001",
                createDateFromString("2024-11-20").toString(), 
                150.0, 
                5, 
                "Publisher B", 
                "ReferenceBook",
                null, // Tình trạng (không dùng cho ReferenceBook)
                20.0 // Tax
        );

        // Act: Gọi phương thức thêm sách
        addBookService.executeAdd(requestData);

        // Assert: Kiểm tra sách được thêm vào database boundary
        verify(mockDBBoundary).saveBook(any(ReferenceBook.class));
        // Kiểm tra kết quả được gửi tới output boundary
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class));
    }

    // Test: Xử lý dữ liệu không hợp lệ (thiếu bookId)
    @Test
    public void testAddBookWithInvalidData() throws Exception {
        // Arrange: Tạo mock cho BookDBBoundary và output boundary
        BookDBBoundary mockDBBoundary = mock(BookDBBoundary.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockDBBoundary, mockOutputBoundary);

        // Dữ liệu không hợp lệ: Thiếu bookId
        AddBookRequestData requestData = new AddBookRequestData(
                null, // Không có bookId
                createDateFromString("2024-11-20").toString(),
                100.0,
                10,
                "Publisher A",
                "TextBook",
                "New",
                0.0);

        // Act: Gọi phương thức thêm sách
        addBookService.executeAdd(requestData);

        // Assert: Kiểm tra kết quả được gửi tới output boundary
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class));
    }

    // Test: Xử lý dữ liệu không hợp lệ (tax không hợp lệ cho ReferenceBook)
    @Test
    public void testAddReferenceBookWithInvalidData() throws Exception {
        // Arrange: Tạo mock cho BookDBBoundary và output boundary
        BookDBBoundary mockDBBoundary = mock(BookDBBoundary.class);
        AddBookOutputBoundary mockOutputBoundary = mock(AddBookOutputBoundary.class);
        AddBookService addBookService = new AddBookService(mockDBBoundary, mockOutputBoundary);

        // Dữ liệu không hợp lệ: Tax âm
        AddBookRequestData requestData = new AddBookRequestData(
                "R001",
                createDateFromString("2024-11-20").toString(),
                150.0,
                5,
                "Publisher B",
                "ReferenceBook",
                null,
                -5.0 // Tax không hợp lệ
        );

        // Act: Gọi phương thức thêm sách
        addBookService.executeAdd(requestData);

        // Assert: Kiểm tra kết quả được gửi tới output boundary
        verify(mockOutputBoundary).presentResult(any(AddBookResponseData.class));
    }
}
