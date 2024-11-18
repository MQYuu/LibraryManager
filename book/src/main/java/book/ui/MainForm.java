package book.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import book.addbookinterface.AddBookPresenter;
import book.database.AddBookMySQL;
import book.usecase.AddBookService;
import book.usecase.BookRepository;

public class MainForm extends JFrame {
    private JButton addBookButton;
    private JButton viewBooksButton;
    private AddBookFormController addBookFormController;

    public MainForm(AddBookFormController addBookFormController) {
        this.addBookFormController = addBookFormController;
        initialize();
    }

    private void initialize() {
        // Set up the main window
        setTitle("Library Management System");
        setSize(500, 300);  // Increased size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the layout manager
        setLayout(new BorderLayout());

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10)); // GridLayout with 3 rows and 1 column, space between buttons
        buttonPanel.setBackground(Color.WHITE);

        // Add "Add Book" button with custom styling
        addBookButton = new JButton("Add Book");
        styleButton(addBookButton);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookFormController.displayAddBookForm();
            }
        });

        // Add "View Books" button with custom styling
        viewBooksButton = new JButton("View Books");
        styleButton(viewBooksButton);
        viewBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add functionality to view books if needed
                JOptionPane.showMessageDialog(MainForm.this, "View Books functionality to be implemented.");
            }
        });

        // Add buttons to the panel
        buttonPanel.add(addBookButton);
        buttonPanel.add(viewBooksButton);

        // Add panel to the main window
        add(buttonPanel, BorderLayout.CENTER);

        // Add a label at the top for the title or introduction
        JLabel titleLabel = new JLabel("Welcome to Library Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));  // Set color for the title
        add(titleLabel, BorderLayout.NORTH);

        // Display the main form
        setVisible(true);
    }

    private void styleButton(JButton button) {
        // Set button font, color, and shape
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180)); // Set button color
        button.setForeground(Color.WHITE);  // Set text color
        button.setFocusPainted(false);  // Remove focus outline
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setPreferredSize(new Dimension(200, 50));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200));  // Lighter shade on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  // Original color
            }
        });

        // Add rounded corners (using a custom border)
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
    }

}

