package book.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import book.editbook.EditBookRequestData;
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

    @Override
    public void saveBook(Book book) {
        String sql = "INSERT INTO books (book_id, entry_date, unit_price, quantity, publisher, type, conditionBook, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        executeUpdateBookSQL(sql, book);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(mapRowToBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateBook(EditBookRequestData editBookRequestData) {
        String sql = "UPDATE books SET entry_date = ?, unit_price = ?, quantity = ?, publisher = ?, conditionBook = ?, tax = ? WHERE book_id = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, editBookRequestData.getEntryDate());
            pstmt.setDouble(2, editBookRequestData.getUnitPrice());
            pstmt.setInt(3, editBookRequestData.getQuantity());
            pstmt.setString(4, editBookRequestData.getPublisher());

            // Set condition or tax based on book type
            if (editBookRequestData.getCondition() != null) {
                pstmt.setString(5, editBookRequestData.getCondition());
                pstmt.setNull(6, java.sql.Types.DOUBLE);
            } else {
                pstmt.setNull(5, java.sql.Types.VARCHAR);
                pstmt.setDouble(6, editBookRequestData.getTax());
            }

            pstmt.setString(7, editBookRequestData.getBookId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated in the database.");
            } else {
                System.out.println("Failed to update the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa sách
    @Override
    public void deleteBook(String bookId) {
        String sql = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No book found with the provided ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Phương thức này để xử lý mã lặp lại trong việc thêm sách
    private void executeUpdateBookSQL(String sql, Book book) {
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

    // Chuyển một kết quả từ ResultSet thành một đối tượng Book
    private Book mapRowToBook(ResultSet resultSet) throws SQLException {
        String bookId = resultSet.getString("book_id");
        String entryDate = resultSet.getString("entry_date");
        double unitPrice = resultSet.getDouble("unit_price");
        int quantity = resultSet.getInt("quantity");
        String publisher = resultSet.getString("publisher");
        String type = resultSet.getString("type");

        if ("TextBook".equals(type)) {
            String condition = resultSet.getString("conditionBook");
            return new TextBook(bookId, entryDate, unitPrice, quantity, publisher, condition);
        } else {
            double tax = resultSet.getDouble("tax");
            return new ReferenceBook(bookId, entryDate, unitPrice, quantity, publisher, tax);
        }
    }

    @Override
    public List<Book> searchBooksById(String id) {
        List<Book> books = new ArrayList<>();
        // Sửa lại câu lệnh SQL để tìm kiếm chính xác theo book_id
        String sql = "SELECT * FROM books WHERE book_id = ?"; // Đảm bảo chỉ so sánh với book_id

        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Gán giá trị book_id vào PreparedStatement
            pstmt.setString(1, id); // Gán giá trị id cho câu lệnh SQL

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    books.add(mapRowToBook(rs)); // Chuyển kết quả thành đối tượng Book
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during book search: " + e.getMessage());
            e.printStackTrace();
        }
        return books; // Trả về danh sách sách tìm được
    }
}
