package library.bookdata;

import java.util.Date;

public class ReferenceBook extends Book {
    private double tax; // Thuế của sách tham khảo

    public ReferenceBook(String bookId, Date dateAdded, double unitPrice, int quantity, String publisher, double tax) {
        super(bookId, dateAdded, unitPrice, quantity, publisher);
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    // Triển khai phương thức tính tổng tiền cho sách tham khảo (có tính thuế)
    @Override
    public double calculateTotalPrice() {
        return getUnitPrice() * getQuantity() + (getUnitPrice() * getQuantity() * tax / 100); // Tổng tiền = đơn giá * số lượng + thuế
    }
}
