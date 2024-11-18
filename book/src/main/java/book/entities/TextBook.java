package book.entities;

public class TextBook extends AddBook {
    private String condition;

    public TextBook(String bookId, String entryDate, double unitPrice, int quantity, String publisher, String condition) {
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
}
