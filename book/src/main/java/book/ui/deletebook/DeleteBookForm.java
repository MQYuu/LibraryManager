package book.ui.deletebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBookForm extends JFrame {
    private JTextField bookIdField;
    private JButton deleteButton;
    private DeleteBookFormController controller;

    public DeleteBookForm(DeleteBookFormController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setTitle("Delete Book");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        setLocationRelativeTo(null); // Căn giữa cửa sổ trên màn hình


        add(new JLabel("Book ID:"));
        bookIdField = new JTextField();
        add(bookIdField);

        deleteButton = new JButton("Delete");
        add(new JLabel()); // Empty cell for layout spacing
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookId = bookIdField.getText();
                controller.deleteBook(bookId);
                dispose();
            }
        });
    }
}
