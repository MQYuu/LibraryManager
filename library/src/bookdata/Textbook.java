package bookdata;
public class Textbook extends Book {
    private String condition; // "new" or "used"

    public Textbook(String bookId, String dateAdded, double unitPrice, int quantity, String publisher, String condition) {
        super(bookId, dateAdded, unitPrice, quantity, publisher);
        this.condition = condition;
    }
}