package book.searchbook;

public class SearchBookRequestData {
    private String bookId;

    public SearchBookRequestData(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
