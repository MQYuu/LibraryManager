package book.addbook;

public class AddBookRequestData {
    private String bookId;
    private String entryDate;
    private double unitPrice;
    private int quantity;
    private String publisher;
    private String type; // "TextBook" or "ReferenceBook"
    private String condition; // only for TextBook
    private double tax; // only for ReferenceBook

    public AddBookRequestData(String bookId, String entryDate, double unitPrice, int quantity, String publisher, String type, String condition, double tax) {
        this.setBookId(bookId);
        this.setEntryDate(entryDate);
        this.setUnitPrice(unitPrice);
        this.setQuantity(quantity);
        this.setPublisher(publisher);
        this.setType(type);
        this.setCondition(condition);
        this.setTax(tax);
    }

    public double getTax() {
        return tax;
        
    }

    public void setTax(double tax) {
        this.tax = tax;
        
    }

    public String getCondition() {
        return condition;
        
    }

    public void setCondition(String condition) {
        this.condition = condition;
        
    }

    public String getPublisher() {
        return publisher;
        
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
        
    }

    public int getQuantity() {
        return quantity;
        
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        
    }

    public double getUnitPrice() {
        return unitPrice;
        
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        
    }

    public String getEntryDate() {
        return entryDate;
        
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
        
    }

    public String getBookId() {
        return bookId;
        
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
        
    }

    public String getType() {
        return type;
        
    }

    public void setType(String type) {
        this.type = type;
        
    }
}
