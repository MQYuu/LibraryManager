package book.ui.deletebook;

import javax.swing.*;
import java.awt.*;

public class DeleteBookResultForm extends JFrame {
    private JLabel resultLabel;

    public DeleteBookResultForm() {
        initialize();
    }

    private void initialize() {
        setTitle("Delete Book Result");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the form
    }

    public void displayResult(String message, boolean success) {
        resultLabel.setText(message);
        resultLabel.setForeground(success ? Color.GREEN : Color.RED);
        setVisible(true);
    }
}
