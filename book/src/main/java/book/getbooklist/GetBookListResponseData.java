package book.getbooklist;

import java.util.List;

import book.entities.Book;

// class chứa danh sách muốn trả về ui
public class GetBookListResponseData {
    private List<Book> bookList;

    public GetBookListResponseData(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }
}
