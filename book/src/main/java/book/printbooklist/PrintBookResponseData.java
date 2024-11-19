package book.printbooklist;

public class PrintBookResponseData {
    private String bookId;
    private String entryDate;
    private double unitPrice;
    private int quantity;
    private String publisher;
    private double tax;
    private String condition;

    // Constructor
    public PrintBookResponseData(String bookId, String entryDate, double unitPrice, int quantity, String publisher, double tax, String condition) {
        this.bookId = bookId;
        this.entryDate = entryDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
        this.tax = tax;
        this.condition = condition;
    }

    // Getter methods
    public String getBookId() {
        return bookId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getTax() {
        return tax;
    }

    public String getCondition() {
        return condition;
    }
}


