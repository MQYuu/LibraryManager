package testusecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import book.database.BookRepository;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.printbooklist.PrintBookOutputBoundary;
import book.printbooklist.PrintBookResponseData;
import book.usecase.PrintBookService;

public class PrintBookServiceTest {
    private BookRepository bookRepository;
    private PrintBookOutputBoundary printBookOutputBoundary;
    private PrintBookService printBookService;

    @BeforeEach
    public void setUp() {
        // Khởi tạo các đối tượng mock
        bookRepository = mock(BookRepository.class);
        printBookOutputBoundary = mock(PrintBookOutputBoundary.class);
        printBookService = new PrintBookService(bookRepository, printBookOutputBoundary);
    }

    @Test
    public void testPrintBook_WithReferenceBookAndTextBook() {
        // Tạo dữ liệu mẫu cho các loại sách
        Book referenceBook = new ReferenceBook("R001", "2022-10-10", 100.0, 5, "Publisher A", 10.0);
        Book textBook = new TextBook("T001", "2022-11-11", 50.0, 10, "Publisher B", "New");

        // Đảm bảo rằng bookRepository trả về danh sách sách mẫu
        List<Book> books = Arrays.asList(referenceBook, textBook);
        when(bookRepository.getAllBooks()).thenReturn(books);

        // Kiểm tra xem các đối tượng sách có được khởi tạo đúng không
        assertNotNull(books);
        assertEquals(2, books.size());

        // Gọi phương thức cần kiểm tra
        printBookService.printBook();

        // Kiểm tra dữ liệu mong đợi
        PrintBookResponseData expectedResponseData1 = new PrintBookResponseData(
                "R001", "2022-10-10", 100.0, 5, "Publisher A", 10.0, "");
        PrintBookResponseData expectedResponseData2 = new PrintBookResponseData(
                "T001", "2022-11-11", 50.0, 10, "Publisher B", 0.0, "New");

        List<PrintBookResponseData> expectedResponseDataList = Arrays.asList(expectedResponseData1,
                expectedResponseData2);

        // Xác minh phương thức presentPrintBookResult được gọi đúng với kết quả mong
        // đợi
        verify(printBookOutputBoundary).presentPrintBookResult(expectedResponseDataList);
    }

    @Test
    public void testPrintBook_WithEmptyBookList() {
        // Đảm bảo rằng bookRepository trả về danh sách rỗng
        when(bookRepository.getAllBooks()).thenReturn(Arrays.asList());

        // Gọi phương thức cần kiểm tra
        printBookService.printBook();

        // Xác minh phương thức presentPrintBookResult được gọi với danh sách rỗng
        verify(printBookOutputBoundary).presentPrintBookResult(Arrays.asList());
    }
}
