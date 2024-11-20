package testusecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import book.database.BookRepository;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.searchbook.SearchBookOutputBoundary;
import book.searchbook.SearchBookRequestData;
import book.searchbook.SearchBookResponseData;
import book.usecase.SearchBookService;

public class SearchBookSeviceTest {
    // Test trường hợp tìm kiếm sách TextBook với bookId hợp lệ
    @Test
    public void testSearchBook_ValidBookId_ReturnsTextBooks() {
        // Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        SearchBookOutputBoundary mockOutputBoundary = mock(SearchBookOutputBoundary.class);

        // Dữ liệu mẫu: Một sách TextBook với bookId là "T001"
        String bookId = "T001";
        List<Book> books = new ArrayList<>();
        books.add(new TextBook("T001", "2024-01-01", 100.0, 10, "Publisher A", "new"));
        when(mockRepository.searchBooksById(bookId)).thenReturn(books);

        // Tạo service và thực hiện hành động tìm kiếm
        SearchBookService service = new SearchBookService(mockRepository, mockOutputBoundary);
        SearchBookRequestData requestData = new SearchBookRequestData(bookId);
        service.searchBook(requestData);

        // Capture kết quả được truyền tới output boundary
        ArgumentCaptor<SearchBookResponseData> captor = ArgumentCaptor.forClass(SearchBookResponseData.class);
        verify(mockOutputBoundary).presentSearchBookResults(captor.capture());

        // Kiểm tra kết quả trả về
        SearchBookResponseData actualResponse = captor.getValue();
        assertEquals(new SearchBookResponseData(books).toString(), actualResponse.toString());
    }

    // Test trường hợp tìm kiếm sách ReferenceBook với bookId hợp lệ
    @Test
    public void testSearchBook_ValidBookId_ReturnsReferenceBooks() {
        // Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        SearchBookOutputBoundary mockOutputBoundary = mock(SearchBookOutputBoundary.class);

        // Dữ liệu mẫu: Một sách ReferenceBook với bookId là "R001"
        String bookId = "R001";
        List<Book> books = new ArrayList<>();
        books.add(new ReferenceBook("R001", "2024-01-01", 150.0, 5, "Publisher B", 20.0));
        when(mockRepository.searchBooksById(bookId)).thenReturn(books);

        // Tạo service và thực hiện hành động tìm kiếm
        SearchBookService service = new SearchBookService(mockRepository, mockOutputBoundary);
        SearchBookRequestData requestData = new SearchBookRequestData(bookId);
        service.searchBook(requestData);

        // Capture kết quả được truyền tới output boundary
        ArgumentCaptor<SearchBookResponseData> captor = ArgumentCaptor.forClass(SearchBookResponseData.class);
        verify(mockOutputBoundary).presentSearchBookResults(captor.capture());

        // Kiểm tra kết quả trả về
        SearchBookResponseData capturedResponse = captor.getValue();
        assertEquals(books, capturedResponse.getFoundBooks());
    }

    // Test trường hợp tìm kiếm với bookId không hợp lệ
    @Test
    public void testSearchBook_InvalidBookId_ReturnsEmptyList() {
        // Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        SearchBookOutputBoundary mockOutputBoundary = mock(SearchBookOutputBoundary.class);

        // Dữ liệu mẫu: bookId không hợp lệ, danh sách trả về rỗng
        String bookId = "INVALID_ID";
        List<Book> emptyList = new ArrayList<>();
        when(mockRepository.searchBooksById(bookId)).thenReturn(emptyList);

        // Tạo service và thực hiện hành động tìm kiếm
        SearchBookService service = new SearchBookService(mockRepository, mockOutputBoundary);
        SearchBookRequestData requestData = new SearchBookRequestData(bookId);
        service.searchBook(requestData);

        // Capture kết quả được truyền tới output boundary
        ArgumentCaptor<SearchBookResponseData> captor = ArgumentCaptor.forClass(SearchBookResponseData.class);
        verify(mockOutputBoundary).presentSearchBookResults(captor.capture());

        // Kiểm tra kết quả trả về
        SearchBookResponseData capturedResponse = captor.getValue();
        assertTrue("Expected foundBooks to be an empty list", capturedResponse.getFoundBooks().isEmpty());
    }

    // Test đảm bảo phương thức searchBooksById của repository được gọi đúng cách
    @Test
    public void testSearchBook_RepositoryMethodCalled() {
        // Tạo mock cho repository và output boundary
        BookRepository mockRepository = mock(BookRepository.class);
        SearchBookOutputBoundary mockOutputBoundary = mock(SearchBookOutputBoundary.class);

        // Tạo service
        SearchBookService service = new SearchBookService(mockRepository, mockOutputBoundary);

        // Thực hiện hành động tìm kiếm
        SearchBookRequestData requestData = new SearchBookRequestData("B123");
        service.searchBook(requestData);

        // Kiểm tra phương thức của repository được gọi với tham số chính xác
        verify(mockRepository).searchBooksById("B123");
    }
}
