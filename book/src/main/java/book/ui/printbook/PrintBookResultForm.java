package book.ui.printbook;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import book.printbooklist.PrintBookResponseData;

public class PrintBookResultForm extends JFrame {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public PrintBookResultForm() {
        // Thiết lập cửa sổ JFrame
        setTitle("Danh Sách Sách");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Đặt cửa sổ ở giữa màn hình
        setLayout(new BorderLayout());

        // Tạo JTextArea để hiển thị thông tin sách
        textArea = new JTextArea();
        textArea.setEditable(false); // Không cho phép chỉnh sửa nội dung
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Đặt khu vực cuộn cho JTextArea
        scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Phương thức để hiển thị thông tin sách dưới dạng văn bản
    public void display(List<PrintBookResponseData> responseData) {
        StringBuilder text = new StringBuilder();
        text.append("Thông Tin Các Sách:\n\n");

        // Xử lý và thêm thông tin của mỗi sách vào chuỗi
        for (PrintBookResponseData data : responseData) {
            text.append("Mã Sách: ").append(data.getBookId()).append("\n");
            text.append("Ngày Nhập: ").append(data.getEntryDate()).append("\n");
            text.append("Đơn Giá: ").append(data.getUnitPrice()).append("\n");
            text.append("Số Lượng: ").append(data.getQuantity()).append("\n");
            text.append("Nhà Xuất Bản: ").append(data.getPublisher()).append("\n");
            text.append("Thuế: ").append(data.getTax()).append("\n");
            text.append("Tình Trạng: ").append(data.getCondition()).append("\n");
            text.append("-----------------------------------\n");
        }

        // Đưa thông tin vào JTextArea
        textArea.setText(text.toString());

        // Hiển thị form
        setVisible(true);
    }
}
