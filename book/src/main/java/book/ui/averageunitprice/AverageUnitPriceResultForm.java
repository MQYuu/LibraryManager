package book.ui.averageunitprice;

import book.averageunitprice.AverageUnitPriceResponseData;

import javax.swing.*;
import java.awt.*;

public class AverageUnitPriceResultForm extends JFrame {
    private JLabel resultLabel;

    public AverageUnitPriceResultForm() {
        setTitle("Trung Bình Đơn Giá Sách Tham Khảo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        resultLabel = new JLabel("Đang tính toán...");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(resultLabel, BorderLayout.CENTER);
    }

    public void display(AverageUnitPriceResponseData responseData) {
        resultLabel.setText("Trung bình đơn giá: " + responseData.getAverageUnitPrice());
        setVisible(true);
    }
}
