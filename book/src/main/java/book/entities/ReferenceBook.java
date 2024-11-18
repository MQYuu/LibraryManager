package book.entities;

public class ReferenceBook extends AddBook {
    private double tax;

    public ReferenceBook(String bookId, String entryDate, double unitPrice, int quantity, String publisher, double tax) {
        super(bookId, entryDate, unitPrice, quantity, publisher);
        this.setTax(tax);
    }

    public double getTax() {
        return tax;
        
    }

    public void setTax(double tax) {
        this.tax = tax;
        
    }

    @Override
    public double calculateTotalPrice() {
        return getQuantity() * getUnitPrice() + getTax();
    }
}
