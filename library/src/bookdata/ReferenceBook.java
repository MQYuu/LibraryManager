package bookdata;
public class ReferenceBook extends Book {
    private double tax;

    public ReferenceBook(String bookId, String dateAdded, double unitPrice, int quantity, String publisher, double tax) {
        super(bookId, dateAdded, unitPrice, quantity, publisher);
        this.tax = tax;
    }

    public double getTotalPriceWithTax() {
        return getTotalPrice() + (getTotalPrice() * tax);
    }
}