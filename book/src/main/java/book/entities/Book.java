package book.entities;

public abstract class Book {
    private String bookId;
    private String entryDate;
    private double unitPrice;
    private int quantity;
    private String publisher;
    
    public Book(String bookId, String entryDate, double unitPrice, int quantity, String publisher) {
        this.bookId = bookId;
        this.entryDate = entryDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.publisher = publisher;
    }
    
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public abstract double calculateTotalPrice();
}
