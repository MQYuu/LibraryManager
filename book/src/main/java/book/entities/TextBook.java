package book.entities;

public class TextBook extends Book {
    private String condition;

    public TextBook(String bookId, String entryDate, double unitPrice, int quantity, String publisher,
            String condition) {
        super(bookId, entryDate, unitPrice, quantity, publisher);
        this.setCondition(condition);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public double calculateTotalPrice() {
        return getCondition().equals("new") ? getQuantity() * getUnitPrice() : getQuantity() * getUnitPrice() * 0.5;
    }

    // Cập nhật phương thức toString() cho TextBook
    @Override
    public String toString() {
        return super.toString() + ", Tình trạng: " + condition;
    }
}
