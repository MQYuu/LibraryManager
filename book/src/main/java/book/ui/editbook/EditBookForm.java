package book.ui.editbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import book.editbook.EditBookRequestData;

public class EditBookForm extends JFrame {
    private JTextField bookIdField;
    private JTextField entryDateField;
    private JTextField unitPriceField;
    private JTextField quantityField;
    private JTextField publisherField;
    private JTextField conditionField;
    private JTextField taxField;
    private JButton saveButton;
    private JComboBox<String> typeComboBox;  // ComboBox for book type
    private EditBookFormController editBookFormController;

    public EditBookForm(EditBookFormController editBookFormController) {
        this.editBookFormController = editBookFormController;
        initialize();
    }

    private void initialize() {
        setTitle("Edit Book Form");
        setSize(400, 350);
        setLayout(new GridLayout(9, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize text fields
        bookIdField = new JTextField();
        entryDateField = new JTextField();
        unitPriceField = new JTextField();
        quantityField = new JTextField();
        publisherField = new JTextField();
        conditionField = new JTextField();
        taxField = new JTextField();
        saveButton = new JButton("Save");

        // ComboBox for selecting book type
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

        // Condition and Tax fields, initially hidden
        add(new JLabel("Condition (new/old):"));
        add(conditionField);
        add(new JLabel("Tax (for ReferenceBook):"));
        add(taxField);

        // Save Button
        add(saveButton);

        // Hide condition and tax fields initially
        conditionField.setVisible(false);
        taxField.setVisible(false);

        // Action listener for typeComboBox
        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFieldsForType();
            }
        });

        // Action listener for Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitEditBookForm();
            }
        });

        updateFieldsForType();  // Update visibility based on initial selection
    }

    private void updateFieldsForType() {
        String selectedType = (String) typeComboBox.getSelectedItem();

        if ("TextBook".equals(selectedType)) {
            conditionField.setVisible(true);
            taxField.setVisible(false);
        } else {
            conditionField.setVisible(false);
            taxField.setVisible(true);
        }
    }

    private void submitEditBookForm() {
        String bookId = bookIdField.getText();
        String entryDate = entryDateField.getText();
        double unitPrice = Double.parseDouble(unitPriceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        String publisher = publisherField.getText();
        String condition = conditionField.getText();
        double tax = taxField.getText().isEmpty() ? 0 : Double.parseDouble(taxField.getText());

        String type = (String) typeComboBox.getSelectedItem();

        // Validate condition for TextBook
        if ("TextBook".equals(type) && condition.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please specify the condition (new/old) for TextBook.");
            return;
        }

        // Validate date format (YYYY-MM-DD)
        if (!isValidDate(entryDate)) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create EditBookRequestData and send it to the editBookFormController
        EditBookRequestData requestData = new EditBookRequestData(
                bookId, entryDate, unitPrice, quantity, publisher, condition, tax
        );

        // Call the editBookFormController to process the book update
        editBookFormController.updateBook(bookId, entryDate, unitPrice, quantity, publisher, condition, tax);

        // Close the form after saving
        this.dispose();
    }

    // Helper method to check date format
    private boolean isValidDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr);  // Try parsing the date
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
