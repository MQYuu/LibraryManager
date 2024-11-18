package library.bookdata;

import java.util.Date;

public class Textbook extends Book {
    private String condition; // Tình trạng sách (mới/cũ)

    public Textbook(String bookId, Date dateAdded, double unitPrice, int quantity, String publisher, String condition) {
        super(bookId, dateAdded, unitPrice, quantity, publisher);
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    // Triển khai phương thức tính tổng tiền cho sách giáo khoa
    @Override
    public double calculateTotalPrice() {
        double totalPrice = getUnitPrice() * getQuantity();
        
        // Nếu sách là cũ, giảm giá 50%
        if ("Old".equalsIgnoreCase(condition)) {
            totalPrice *= 0.5;
        }
        
        return totalPrice;
    }
}
