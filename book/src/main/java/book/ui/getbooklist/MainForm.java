package book.ui.getbooklist;

import javax.swing.*;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;
import book.ui.addbook.AddBookFormController;
import book.ui.editbook.EditBookFormController;
import book.ui.printbook.PrintBookFormController;
import book.ui.searchbook.SearchBookFormController;
import book.ui.deletebook.DeleteBookFormController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame {
    private JButton addBookButton;
    private JButton editBookButton;
    private JButton deleteBookButton; // Nút xóa sách
    private JButton searchBookButton;
    private JButton printBookButton; // Nút in sách

    private AddBookFormController addBookFormController;
    private EditBookFormController editBookFormController;
    private DeleteBookFormController deleteBookFormController; // Đối tượng DeleteBookFormController
    private SearchBookFormController searchBookFormController; // Đối tượng SearchBookFormController
    private PrintBookFormController printBookFormController;

    private JTable booksTable; // Bảng để hiển thị sách
    private DefaultTableModel tableModel;

    public MainForm(AddBookFormController addBookFormController, EditBookFormController editBookFormController,
            DeleteBookFormController deleteBookFormController, SearchBookFormController searchBookFormController,
            PrintBookFormController printBookFormController) {
        this.addBookFormController = addBookFormController;
        this.editBookFormController = editBookFormController;
        this.deleteBookFormController = deleteBookFormController; // Khởi tạo DeleteBookFormController
        this.searchBookFormController = searchBookFormController; // Khởi tạo SearchBookFormController
        this.printBookFormController = printBookFormController; // Khởi tạo PrintBookFormController
        initialize();
    }

    private void initialize() {
        setTitle("Hệ Thống Quản Lý Thư Viện");
        setSize(800, 500); // Tăng kích thước để chứa bảng
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Thêm một dòng nữa cho nút tìm kiếm
        buttonPanel.setBackground(Color.WHITE);

        // Nút Thêm Sách
        addBookButton = new JButton("Thêm Sách");
        styleButton(addBookButton);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookFormController.displayAddBookForm();
            }
        });

        // Nút Sửa Sách
        editBookButton = new JButton("Sửa Sách");
        styleButton(editBookButton);
        editBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = booksTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String bookId = (String) booksTable.getValueAt(selectedRow, 0);
                    editBookFormController.displayEditBookForm(bookId);
                } else {
                    JOptionPane.showMessageDialog(MainForm.this, "Vui lòng chọn một sách để chỉnh sửa.");
                }
            }
        });

        // Nút Xóa Sách
        deleteBookButton = new JButton("Xóa Sách");
        styleButton(deleteBookButton);
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi phương thức mở form xóa sách
                deleteBookFormController.openDeleteBookForm();
            }
        });

        // Nút Tìm Kiếm Sách
        searchBookButton = new JButton("Tìm Kiếm Sách");
        styleButton(searchBookButton);
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookFormController.openSearchBookForm(); // Gọi Controller để mở form tìm kiếm
            }
        });

        // Nút In Sách
        printBookButton = new JButton("In Sách");
        styleButton(printBookButton);
        printBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBookFormController.printBook(); // Gọi phương thức in từ PrintBookFormController
            }
        });

        buttonPanel.add(addBookButton);
        buttonPanel.add(editBookButton);
        buttonPanel.add(deleteBookButton); // Thêm nút xóa vào panel
        buttonPanel.add(searchBookButton); // Thêm nút tìm kiếm vào panel
        buttonPanel.add(printBookButton);  // Thêm nút In Sách vào panel

        add(buttonPanel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Chào Mừng Bạn Đến Hệ Thống Quản Lý Thư Viện", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        add(titleLabel, BorderLayout.NORTH);

        // Khởi tạo bảng và model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã Sách");
        tableModel.addColumn("Ngày Nhập");
        tableModel.addColumn("Đơn Giá");
        tableModel.addColumn("Số Lượng");
        tableModel.addColumn("Nhà Xuất Bản");
        tableModel.addColumn("Thuế");
        tableModel.addColumn("Tình Trạng");

        booksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(booksTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
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

    // phương thức hiển thị danh sách sách lên giao diện
    public void displayBookList(List<Book> books) {
        DefaultTableModel tableModel = (DefaultTableModel) booksTable.getModel();
        tableModel.setRowCount(0); // Xóa tất cả dòng cũ trong bảng

        for (Book book : books) {
            // Thêm dòng dữ liệu vào bảng
            Object[] rowData = new Object[7];
            rowData[0] = book.getBookId();
            rowData[1] = book.getEntryDate();
            rowData[2] = book.getUnitPrice();
            rowData[3] = book.getQuantity();
            rowData[4] = book.getPublisher();

            // Kiểm tra loại sách để hiển thị thông tin thuế và tình trạng
            if (book instanceof ReferenceBook) {
                rowData[5] = ((ReferenceBook) book).getTax(); // Thuế
                rowData[6] = ""; // Tình trạng không có
            } else if (book instanceof TextBook) {
                rowData[5] = ""; // Thuế không có
                rowData[6] = ((TextBook) book).getCondition(); // Tình trạng
            }

            tableModel.addRow(rowData); // Thêm vào bảng
        }

        // Gọi revalidate và repaint để đảm bảo bảng được cập nhật lại đúng cách
        booksTable.revalidate();
        booksTable.repaint();
    }
}
