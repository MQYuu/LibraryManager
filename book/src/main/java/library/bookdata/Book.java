package library.bookdata;

import java.util.Date;

public abstract class Book {
    private String bookId;
    private Date dateAdded;
    private double unitPrice;
    private int quantity;
    private String publisher;
    
    
    public Book(String bookId, Date dateAdded, double unitPrice, int quantity, String publisher) {
        this.bookId = bookId;
        this.dateAdded = dateAdded;
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
    
    public Date getDateAdded() {
        return dateAdded;
    }
    
    public void setDateAdded(Date dateAdded) {
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
    
    
    public abstract double calculateTotalPrice();
    
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
