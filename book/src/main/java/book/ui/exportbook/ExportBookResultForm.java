package book.ui.exportbook;

import book.entities.Book;
import book.exportbook.ExportBookResponseData;

public class ExportBookResultForm {
    public void display(ExportBookResponseData responseData) {
        for (Book book : responseData.getBooks()) {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Publisher: " + book.getPublisher());
            System.out.println("Price: " + book.getUnitPrice());
            // Hiển thị các thông tin khác của sách
        }
    }
}
