package book.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;

public class BookMySQL implements BookDBBoundary {
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

    // Hàm tạo kết nối cơ sở dữ liệu
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Hàm kiểm tra kết nối với cơ sở dữ liệu
    public boolean checkDatabaseConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connected to the database successfully.");
                return true;
            } else {
                System.out.println("Failed to connect to the database.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void saveBook(Book book) {
        String sql = "INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, conditionBook, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getBookId());
            pstmt.setString(2, book.getEntryDate());
            pstmt.setDouble(3, book.getUnitPrice());
            pstmt.setInt(4, book.getQuantity());
            pstmt.setString(5, book.getPublisher());
            pstmt.setString(6, (book instanceof TextBook) ? "TextBook" : "ReferenceBook");

            if (book instanceof TextBook) {
                pstmt.setString(7, ((TextBook) book).getCondition());
                pstmt.setNull(8, java.sql.Types.DOUBLE);
            } else if (book instanceof ReferenceBook) {
                pstmt.setNull(7, java.sql.Types.VARCHAR);
                pstmt.setDouble(8, ((ReferenceBook) book).getTax());
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book saved to the database.");
            } else {
                System.out.println("Failed to save the book.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String bookId = rs.getString("book_id");
                String entryDate = rs.getString("entry_date"); 
                double unitPrice = rs.getDouble("unit_price");
                int quantity = rs.getInt("quantity");
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");

                Book book;
                if ("TextBook".equals(type)) {
                    String condition = rs.getString("conditionBook");
                    book = new TextBook(bookId, entryDate, unitPrice, quantity, publisher, condition);
                } else {
                    double tax = rs.getDouble("tax");
                    book = new ReferenceBook(bookId, entryDate, unitPrice, quantity, publisher, tax);
                }

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
