package book.ui.editbook;

import javax.swing.JFrame;

import javax.swing.*;
import java.awt.*;

public class EditBookResultForm extends JFrame {
    private JLabel resultLabel;
    
    public EditBookResultForm() {
        initialize();
    }

    private void initialize() {
        setTitle("Edit Book Result");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the form
    }

    // Method to display the result
    public void displayResult(String message, boolean success) {
        resultLabel.setText(message);
        resultLabel.setForeground(success ? Color.GREEN : Color.RED);
        setVisible(true);
    }
}


