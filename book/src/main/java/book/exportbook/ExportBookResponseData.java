package book.exportbook;

import java.util.List;

import book.entities.Book;

public class ExportBookResponseData {
    private List<Book> books;

    public ExportBookResponseData(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
