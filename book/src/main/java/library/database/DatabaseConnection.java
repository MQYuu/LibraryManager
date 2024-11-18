package library.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import library.bookdata.Book;
import library.bookdata.ReferenceBook;
import library.bookdata.Textbook;

public class DatabaseConnection implements BookDatabaseBoundary {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    // Establish a connection to the database
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";  // Adjust the table and columns as necessary

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String bookId = rs.getString("bookId");
                Date dateAdded = rs.getDate("dateAdded");
                double unitPrice = rs.getDouble("unitPrice");
                int quantity = rs.getInt("quantity");
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");

                if ("Reference".equalsIgnoreCase(type)) {
                    double tax = rs.getDouble("tax");
                    books.add(new ReferenceBook(bookId, dateAdded, unitPrice, quantity, publisher, tax));
                } else if ("Textbook".equalsIgnoreCase(type)) {
                    String condition = rs.getString("bookCondition");
                    books.add(new Textbook(bookId, dateAdded, unitPrice, quantity, publisher, condition));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Book getBookById(String bookId) {
        String query = "SELECT * FROM books WHERE bookId = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String dateAdded = rs.getString("dateAdded");
                    double unitPrice = rs.getDouble("unitPrice");
                    int quantity = rs.getInt("quantity");
                    String publisher = rs.getString("publisher");
                    String type = rs.getString("type");

                    if ("Reference".equalsIgnoreCase(type)) {
                        double tax = rs.getDouble("tax");
                        return new ReferenceBook(bookId, Date.valueOf(dateAdded), unitPrice, quantity, publisher, tax);
                    } else if ("Textbook".equalsIgnoreCase(type)) {
                        String condition = rs.getString("bookCondition");
                        return new Textbook(bookId, Date.valueOf(dateAdded), unitPrice, quantity, publisher, condition);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no book found
    }

    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO books (bookId, dateAdded, unitPrice, quantity, publisher, type, bookCondition, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getBookId());
            stmt.setDate(2, new java.sql.Date(book.getDateAdded().getTime()));
            stmt.setDouble(3, book.getUnitPrice());
            stmt.setInt(4, book.getQuantity());
            stmt.setString(5, book.getPublisher());

            if (book instanceof ReferenceBook) {
                stmt.setString(6, "Reference");
                stmt.setString(7, null);  // No condition for reference books
                stmt.setDouble(8, ((ReferenceBook) book).getTax());
            } else if (book instanceof Textbook) {
                stmt.setString(6, "Textbook");
                stmt.setString(7, ((Textbook) book).getCondition());
                stmt.setNull(8, Types.DOUBLE);  // No tax for textbooks
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        String query = "UPDATE books SET dateAdded = ?, unitPrice = ?, quantity = ?, publisher = ?, bookCondition = ?, tax = ? WHERE bookId = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(book.getDateAdded().getTime()));
            stmt.setDouble(2, book.getUnitPrice());
            stmt.setInt(3, book.getQuantity());
            stmt.setString(4, book.getPublisher());
            stmt.setString(5, (book instanceof Textbook) ? ((Textbook) book).getCondition() : null);
            stmt.setDouble(6, (book instanceof ReferenceBook) ? ((ReferenceBook) book).getTax() : 0);
            stmt.setString(7, book.getBookId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBook(String bookId) {
        String query = "DELETE FROM books WHERE bookId = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
