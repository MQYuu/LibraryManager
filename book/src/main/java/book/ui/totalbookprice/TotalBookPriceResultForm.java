package book.ui.totalbookprice;

import javax.swing.*;
import java.awt.*;

public class TotalBookPriceResultForm extends JFrame {
    private JLabel totalPriceLabel;

    public TotalBookPriceResultForm() {
        setTitle("Tổng Giá Trị Sách");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        totalPriceLabel = new JLabel("Tổng Giá Trị: ");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(totalPriceLabel, BorderLayout.CENTER);
    }

    public void displayTotalPrice(double totalPrice) {
        totalPriceLabel.setText("Tổng Giá Trị: " + totalPrice + " VND");
        setVisible(true);
    }
}
