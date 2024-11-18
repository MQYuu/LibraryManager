package bookdata;
public abstract class Book {
    private String bookId;
    private String dateAdded;
    private double unitPrice;
    private int quantity;
    private String publisher;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book(String bookId, String dateAdded, double unitPrice, int quantity, String publisher) {
        this.bookId = bookId;
        this.dateAdded = dateAdded;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    public String getPublisher() {
        return publisher;
    }
}
