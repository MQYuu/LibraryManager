package book.ui.searchbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBookForm extends JFrame {
    private JTextField bookIdField;
    private JButton searchButton;
    private SearchBookFormController searchBookFormController;

    public SearchBookForm(SearchBookFormController searchBookFormController) {
        this.searchBookFormController = searchBookFormController;
        initialize();
    }

    private void initialize() {
        setTitle("Search Book");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());
        setLocationRelativeTo(null); // Căn giữa cửa sổ trên màn hình


        add(new JLabel("Nhập id cần tìm:"));
        bookIdField = new JTextField(20);
        add(bookIdField);

        searchButton = new JButton("Search");
        add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookId = bookIdField.getText();
                searchBookFormController.searchBook(bookId);
                dispose();
            }
        });
    }
}
