package book.editbook;

public class EditBookRequestData {
    private String bookId;
    private String entryDate;
    private double unitPrice;
    private int quantity;
    private String publisher;
    private String condition;
    private double tax;

    public EditBookRequestData(String bookId, String entryDate, double unitPrice, int quantity, String publisher, String condition, double tax) {
        this.bookId = bookId;
        this.entryDate = entryDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
        this.condition = condition;
        this.tax = tax;
    }

    // Getter methods
    public String getBookId() { return bookId; }
    public String getEntryDate() { return entryDate; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public String getPublisher() { return publisher; }
    public String getCondition() { return condition; }
    public double getTax() { return tax; }
}
