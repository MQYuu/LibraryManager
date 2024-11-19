package book.searchbook;

public class SearchBookRequestData {
    private String keyword;

    public SearchBookRequestData(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
