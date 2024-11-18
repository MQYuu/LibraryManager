package book.entities;

public class ReferenceBook extends Book {
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

    // Cập nhật phương thức toString() cho ReferenceBook
    @Override
    public String toString() {
        return super.toString() + ", Thuế: " + tax;
    }
}
