import java.util.List;

import bookdata.Book;
import bookdata.ReferenceBook;
import bookdata.Textbook;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();

        // Thêm sách
        System.out.println("=== Thêm sách ===");
        Book textbook = new Textbook("T001", "2024-11-10", 10.5, 100, "Publisher A", "new");
        Book referenceBook = new ReferenceBook("R001", "2024-11-12", 15.0, 50, "Publisher B", 0.05);
        
        manager.addBook(textbook);
        System.out.println("Đã thêm sách giáo khoa: " + textbook.getBookId());
        
        manager.addBook(referenceBook);
        System.out.println("Đã thêm sách tham khảo: " + referenceBook.getBookId());

        // Xóa sách
        System.out.println("\n=== Xóa sách ===");
        boolean isRemoved = manager.removeBook("T001");
        if (isRemoved) {
            System.out.println("Đã xóa sách có mã T001");
        } else {
            System.out.println("Không tìm thấy sách có mã T001 để xóa");
        }

        // Sửa thông tin sách
        System.out.println("\n=== Sửa thông tin sách ===");
        manager.updateBook("R001", 16.0, 60);
        System.out.println("Đã cập nhật sách có mã R001 với đơn giá mới là 16.0 và số lượng mới là 60");

        // Tìm kiếm sách theo nhà xuất bản
        System.out.println("\n=== Tìm kiếm sách theo nhà xuất bản ===");
        String publisherToSearch = "Publisher B";
        List<Book> booksFromPublisherB = manager.searchByPublisher(publisherToSearch);
        System.out.println("Tìm thấy " + booksFromPublisherB.size() + " sách từ nhà xuất bản " + publisherToSearch);

        // Tính tổng thành tiền cho từng loại sách
        System.out.println("\n=== Tính tổng thành tiền cho từng loại sách ===");
        double totalPriceTextbooks = manager.calculateTotalPrice(Textbook.class);
        double totalPriceReferenceBooks = manager.calculateTotalPrice(ReferenceBook.class);
        System.out.println("Tổng thành tiền của sách giáo khoa: " + totalPriceTextbooks);
        System.out.println("Tổng thành tiền của sách tham khảo: " + totalPriceReferenceBooks);

        // Tính trung bình cộng đơn giá sách tham khảo
        System.out.println("\n=== Tính trung bình cộng đơn giá sách tham khảo ===");
        double averageUnitPriceReference = manager.calculateAverageUnitPriceForReferenceBooks();
        System.out.println("Trung bình cộng đơn giá của sách tham khảo: " + averageUnitPriceReference);

        // Xuất sách giáo khoa của nhà xuất bản X
        System.out.println("\n=== Xuất sách giáo khoa của nhà xuất bản X ===");
        String publisherToExport = "Publisher A";
        List<Textbook> textbooksPublisherA = manager.exportTextbooksByPublisher(publisherToExport);
        System.out.println("Số lượng sách giáo khoa của nhà xuất bản " + publisherToExport + ": " + textbooksPublisherA.size());
        
        for (Textbook tb : textbooksPublisherA) {
            System.out.println(" - Sách giáo khoa mã: " + tb.getBookId() + ", Nhà xuất bản: " + tb.getPublisher());
        }
    }
}
