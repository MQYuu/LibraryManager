package book.searchbook;

public class SearchBookRequestData {
    private String id;

    public SearchBookRequestData(String id) {
        this.id = id;
    }

    public String getBookId() {
        return id;
    }
}
