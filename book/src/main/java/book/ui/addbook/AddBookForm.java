package book.ui.addbook;

import javax.swing.*;

import book.addbook.AddBookRequestData;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddBookForm extends JFrame {
    private JTextField bookIdField;
    private JTextField entryDateField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextField publisherField;
    private JTextField conditionField;
    private JTextField taxField;
    private JButton submitButton;
    private JComboBox<String> typeComboBox; // ComboBox to select book type

    private AddBookFormController controller;

    public AddBookForm(AddBookFormController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setTitle("Add Book Form");
        setSize(400, 350);
        setLayout(new GridLayout(9, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        bookIdField = new JTextField();
        entryDateField = new JTextField();
        unitPriceField = new JTextField();
        quantityField = new JTextField();
        publisherField = new JTextField();
        conditionField = new JTextField();
        taxField = new JTextField();
        submitButton = new JButton("Submit");

        // ComboBox for book type selection
        typeComboBox = new JComboBox<>(new String[] { "TextBook", "ReferenceBook" });

        add(new JLabel("Book ID:"));
        add(bookIdField);
        add(new JLabel("Entry Date (YYYY-MM-DD):"));
        add(entryDateField);
        add(new JLabel("Unit Price:"));
        add(unitPriceField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Publisher:"));
        add(publisherField);
        add(new JLabel("Book Type:"));
        add(typeComboBox);

        // Condition and Tax fields are hidden initially
        add(new JLabel("Condition (new/old):"));
        add(conditionField);
        add(new JLabel("Tax (for ReferenceBook):"));
        add(taxField);

        // Submit Button
        add(submitButton);

        // Hide the fields initially
        conditionField.setVisible(false);
        taxField.setVisible(false);

        // Action listener for ComboBox
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFieldsForType();
            }
        });

        // Submit button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitBook();
            }
        });

        updateFieldsForType(); // To update the visibility based on the initial selection
    }

    private void updateFieldsForType() {
        String selectedType = (String) typeComboBox.getSelectedItem();

        if ("TextBook".equals(selectedType)) {
            // If TextBook, show condition field and hide tax field
            conditionField.setVisible(true);
            taxField.setVisible(false);
        } else {
            // If ReferenceBook, show tax field and hide condition field
            conditionField.setVisible(false);
            taxField.setVisible(true);
        }
    }

    private void submitBook() {
        String bookId = bookIdField.getText();
        String entryDate = entryDateField.getText();
        double unitPrice = Double.parseDouble(unitPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        String publisher = publisherField.getText();
        String condition = conditionField.getText();
        double tax = taxField.getText().isEmpty() ? 0 : Double.parseDouble(taxField.getText());
    
        String type = (String) typeComboBox.getSelectedItem();
    
        // If it's a TextBook, ensure condition is filled in
        if ("TextBook".equals(type) && condition.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please specify the condition (new/old) for TextBook.");
            return;
        }
    
        // Validate the entry date format (YYYY-MM-DD)
        if (!isValidDate(entryDate)) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Tạo đối tượng AddBookRequestData và gửi yêu cầu
        AddBookRequestData requestData = new AddBookRequestData(
                bookId, entryDate, unitPrice, quantity, publisher, type, condition, tax
        );
    
        // Gọi controller để xử lý việc thêm sách
        controller.submitAddBookForm(requestData);
    
        // Đóng form sau khi nhập thành công
        this.dispose();  // Đóng cửa sổ AddBookForm
    }
    

    // Helper method to check date format
    private boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr); // Try to parse the date
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
