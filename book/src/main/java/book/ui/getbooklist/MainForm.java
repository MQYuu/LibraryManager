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
import book.ui.totalbookprice.TotalBookPriceFormController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame {
    private JButton addBookButton;
    private JButton editBookButton;
    private JButton deleteBookButton;
    private JButton searchBookButton;
    private JButton printBookButton;
    private JButton calculateTotalPriceButton; // Nút tính tổng giá sách

    private AddBookFormController addBookFormController;
    private EditBookFormController editBookFormController;
    private DeleteBookFormController deleteBookFormController;
    private SearchBookFormController searchBookFormController;
    private PrintBookFormController printBookFormController;
    private TotalBookPriceFormController totalBookPriceFormController; // Đối tượng TotalBookPriceFormController

    private JTable booksTable;
    private DefaultTableModel tableModel;

    public MainForm(AddBookFormController addBookFormController, EditBookFormController editBookFormController,
                    DeleteBookFormController deleteBookFormController, SearchBookFormController searchBookFormController,
                    PrintBookFormController printBookFormController, TotalBookPriceFormController totalBookPriceFormController) {
        this.addBookFormController = addBookFormController;
        this.editBookFormController = editBookFormController;
        this.deleteBookFormController = deleteBookFormController;
        this.searchBookFormController = searchBookFormController;
        this.printBookFormController = printBookFormController;
        this.totalBookPriceFormController = totalBookPriceFormController; // Khởi tạo TotalBookPriceFormController
        initialize();
    }

    private void initialize() {
        setTitle("Hệ Thống Quản Lý Thư Viện");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));
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
                deleteBookFormController.openDeleteBookForm();
            }
        });

        // Nút Tìm Kiếm Sách
        searchBookButton = new JButton("Tìm Kiếm Sách");
        styleButton(searchBookButton);
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookFormController.openSearchBookForm();
            }
        });

        // Nút In Sách
        printBookButton = new JButton("In Sách");
        styleButton(printBookButton);
        printBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBookFormController.printBook();
            }
        });

        // Nút Tính Tổng Giá Sách
        calculateTotalPriceButton = new JButton("Tính Tổng Giá Sách");
        styleButton(calculateTotalPriceButton);
        calculateTotalPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalBookPriceFormController.calculateTotalBookPrice(); // Gọi phương thức tính tổng giá sách
            }
        });

        buttonPanel.add(addBookButton);
        buttonPanel.add(editBookButton);
        buttonPanel.add(deleteBookButton);
        buttonPanel.add(searchBookButton);
        buttonPanel.add(printBookButton);
        buttonPanel.add(calculateTotalPriceButton);  // Thêm nút tính tổng giá sách vào panel

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
    }

    // phương thức hiển thị danh sách sách lên giao diện
    public void displayBookList(List<Book> books) {
        DefaultTableModel tableModel = (DefaultTableModel) booksTable.getModel();
        tableModel.setRowCount(0);

        for (Book book : books) {
            Object[] rowData = new Object[7];
            rowData[0] = book.getBookId();
            rowData[1] = book.getEntryDate();
            rowData[2] = book.getUnitPrice();
            rowData[3] = book.getQuantity();
            rowData[4] = book.getPublisher();

            if (book instanceof ReferenceBook) {
                rowData[5] = ((ReferenceBook) book).getTax();
                rowData[6] = "";
            } else if (book instanceof TextBook) {
                rowData[5] = "";
                rowData[6] = ((TextBook) book).getCondition();
            }

            tableModel.addRow(rowData);
        }

        booksTable.revalidate();
        booksTable.repaint();
    }
}
