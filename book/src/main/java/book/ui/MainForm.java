package book.ui;

import javax.swing.*;
import book.entities.Book;
import book.ui.addbook.AddBookFormController;
import book.ui.getbooklist.MainFormController;
import book.getbooklist.GetBookListResponseData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JButton addBookButton;
    private JButton viewBooksButton;
    private AddBookFormController addBookFormController;
    private MainFormController mainFormController;
    private JList<String> booksList;  // Danh sách để hiển thị tiêu đề sách

    public MainForm(AddBookFormController addBookFormController, MainFormController mainFormController) {
        this.addBookFormController = addBookFormController;
        this.mainFormController = mainFormController;
        initialize();
    }

    private void initialize() {
        setTitle("Hệ Thống Quản Lý Thư Viện");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        addBookButton = new JButton("Thêm Sách");
        styleButton(addBookButton);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookFormController.displayAddBookForm();
            }
        });

        viewBooksButton = new JButton("Xem Sách");
        styleButton(viewBooksButton);
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBookList(null);  // Lấy và hiển thị sách khi nhấn nút "Xem Sách"
            }
        });

        buttonPanel.add(addBookButton);
        buttonPanel.add(viewBooksButton);

        add(buttonPanel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Chào Mừng Bạn Đến Hệ Thống Quản Lý Thư Viện", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        add(titleLabel, BorderLayout.NORTH);

        // Khởi tạo và thêm danh sách sách vào giao diện chính
        booksList = new JList<>();
        add(new JScrollPane(booksList), BorderLayout.CENTER);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setPreferredSize(new Dimension(200, 50));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
    }

    public void displayBookList(List<Book> books) {
        // Khởi tạo list model
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Duyệt qua danh sách sách và thêm vào list model
        for (Book book : books) {
            listModel.addElement(book.getBookId());  // Giả sử Book có phương thức getBookId() để lấy ID của sách
        }

        // Cập nhật model của list
        booksList.setModel(listModel);
    }
}
