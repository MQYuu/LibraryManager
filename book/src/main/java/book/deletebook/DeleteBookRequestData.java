package book.deletebook;

public class DeleteBookRequestData {
    private String bookId;

    public DeleteBookRequestData(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}

