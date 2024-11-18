package book.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import book.addbook.AddBookDBBoundary;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;

public class BookMySQL implements AddBookDBBoundary {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/LibraryDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    public BookMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra kết nối với cơ sở dữ liệu
    public boolean checkDatabaseConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to the database successfully.");
                return true; // Kết nối thành công
            } else {
                System.out.println("Failed to connect to the database.");
                return false; // Kết nối không thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Lỗi kết nối
        }
    }

    @Override
    public void saveBook(Book book) {
        String sql = "INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, conditionBook, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getBookId());
            pstmt.setString(2, book.getEntryDate());
            pstmt.setDouble(3, book.getUnitPrice());
            pstmt.setInt(4, book.getQuantity());
            pstmt.setString(5, book.getPublisher());
            pstmt.setString(6, (book instanceof TextBook) ? "TextBook" : "ReferenceBook");

            if (book instanceof TextBook) {
                pstmt.setString(7, ((TextBook) book).getCondition()); // conditionBook cho TextBook
                pstmt.setNull(8, java.sql.Types.DOUBLE); // Thuế cho TextBook không áp dụng
            } else if (book instanceof ReferenceBook) {
                pstmt.setNull(7, java.sql.Types.VARCHAR); // conditionBook cho ReferenceBook không áp dụng
                pstmt.setDouble(8, ((ReferenceBook) book).getTax()); // Thuế cho ReferenceBook
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book saved to the database.");
            } else {
                System.out.println("Failed to save the book.");
            }
        } catch (SQLException e) {
            // In ra thông báo chi tiết lỗi
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
