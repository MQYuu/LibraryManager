package book.ui.searchbook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBookForm extends JFrame {
    private JTextField keywordField;
    private JButton searchButton;
    private SearchBookFormController controller;

    public SearchBookForm(SearchBookFormController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setTitle("Search Book");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());
        setLocationRelativeTo(null); // Căn giữa cửa sổ trên màn hình


        add(new JLabel("Nhập id cần tìm:"));
        keywordField = new JTextField(20);
        add(keywordField);

        searchButton = new JButton("Search");
        add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = keywordField.getText();
                controller.searchBook(keyword);
                dispose();
            }
        });
    }
}
