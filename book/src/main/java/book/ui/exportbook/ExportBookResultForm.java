package book.ui.exportbook;

import java.util.List;

import javax.swing.*;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.exportbook.ExportBookResponseData;

public class ExportBookResultForm extends JFrame {
    private JTextArea resultArea;

    public ExportBookResultForm() {
        setTitle("Export Book Results");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo một JTextArea để hiển thị kết quả
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane);
    }

    public void display(ExportBookResponseData responseData) {
        List<Book> books = responseData.getBooks();
        if (books.isEmpty()) {
            resultArea.setText("No books found for the given publisher.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Book book : books) {
                sb.append("Mã sách: ").append(book.getBookId()).append("\n")
                        .append("Ngày nhập: ").append(book.getEntryDate()).append("\n")
                        .append("Đơn giá: ").append(book.getUnitPrice()).append("\n")
                        .append("Số lượng: ").append(book.getQuantity()).append("\n")
                        .append("Publisher: ").append(book.getPublisher()).append("\n")
                        .append("Price: ").append(book.getUnitPrice()).append("\n");

                // Kiểm tra và hiển thị loại sách
                if (book instanceof TextBook) {
                    sb.append("Type: TextBook\n");
                } else if (book instanceof ReferenceBook) {
                    sb.append("Type: ReferenceBook\n");
                }

                sb.append("------------------------------------\n");
            }
            resultArea.setText(sb.toString());
        }
        setVisible(true); // Hiển thị form
    }

}
