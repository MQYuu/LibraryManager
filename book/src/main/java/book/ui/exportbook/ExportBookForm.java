package book.ui.exportbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportBookForm extends JFrame {
    private JTextField publisherField;
    private JButton submitButton;
    private ExportBookFormController controller;

    public ExportBookForm(ExportBookFormController controller) {
        this.controller = controller;

        setTitle("Enter Publisher");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo các thành phần giao diện
        JLabel label = new JLabel("Publisher:");
        publisherField = new JTextField(20);
        submitButton = new JButton("Submit");

        // Đặt layout cho form
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(publisherField);
        panel.add(submitButton);

        add(panel);

        // Lắng nghe sự kiện khi người dùng nhấn nút "Submit"
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String publisher = publisherField.getText();
                if (!publisher.isEmpty()) {
                    controller.handlePublisherInput(publisher);
                    dispose();  // Đóng form sau khi nhấn "Submit"
                } else {
                    JOptionPane.showMessageDialog(ExportBookForm.this, "Please enter a publisher name.");
                }
            }
        });
    }
}

