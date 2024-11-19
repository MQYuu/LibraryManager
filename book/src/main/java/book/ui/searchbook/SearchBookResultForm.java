package book.ui.searchbook;

import javax.swing.*;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;

import java.awt.*;
import java.util.List;

public class SearchBookResultForm extends JFrame {
    private JTextArea resultArea;

    public SearchBookResultForm() {
        initialize();
    }

    private void initialize() {
        setTitle("Search Results");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

public void displayResults(List<Book> books) {
    resultArea.setText("");  // Xóa kết quả cũ
    if (books.isEmpty()) {
        resultArea.append("Không tìm thấy sách.\n");
    } else {
        for (Book book : books) {
            resultArea.append("Mã sách: " + book.getBookId() + "\n");
            resultArea.append("Ngày nhập: " + book.getEntryDate() + "\n");
            resultArea.append("Đơn giá: " + book.getUnitPrice() + "\n");
            resultArea.append("Số lượng: " + book.getQuantity() + "\n");
            resultArea.append("Nhà xuất bản: " + book.getPublisher() + "\n");

            // Kiểm tra nếu là sách tham khảo (ReferenceBook)
            if (book instanceof ReferenceBook) {
                resultArea.append("Thuế: " + ((ReferenceBook) book).getTax() + "\n");
            }
            // Kiểm tra nếu là sách giáo khoa (TextBook)
            else if (book instanceof TextBook) {
                resultArea.append("Tình trạng: " + ((TextBook) book).getCondition() + "\n");
            }

            resultArea.append("---------------------------------\n");
        }
    }
    setVisible(true);  // Hiển thị kết quả
}

}
