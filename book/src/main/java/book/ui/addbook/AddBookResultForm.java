package book.ui.addbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookResultForm extends JFrame {
    private JLabel resultLabel;
    private JButton closeButton;

    public AddBookResultForm() {
        initialize();
    }

    private void initialize() {
        setTitle("Kết quả thêm sách");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        resultLabel = new JLabel("", SwingConstants.CENTER);
        closeButton = new JButton("Đóng");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(resultLabel, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }

    /**
     * Phương thức hiển thị kết quả trên form.
     * @param message Thông báo cần hiển thị (thành công hoặc lỗi).
     */
    public void displayResult(String message) {
        resultLabel.setText(message);
        setVisible(true);
    }
}
