package library.usecase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import library.bookdata.Book;
import library.bookdata.Textbook;

class BookManager {
    private List<Book> books = new ArrayList<>();

    private AddBookUseCase addBookUseCase;
    private RemoveBookUseCase removeBookUseCase;
    private UpdateBookUseCase updateBookUseCase;
    private SearchByIDUseCase searchByIDUseCase;
    private CalculateTotalPriceUseCase calculateTotalPriceUseCase;
    private CalculateAverageUnitPriceForReferenceBooksUseCase calculateAverageUnitPriceUseCase;
    private ExportTextbooksByPublisherUseCase exportTextbooksUseCase;

    public BookManager() {
        this.addBookUseCase = new AddBookUseCase(books);
        this.removeBookUseCase = new RemoveBookUseCase(books);
        this.updateBookUseCase = new UpdateBookUseCase(books);
        this.searchByIDUseCase = new SearchByIDUseCase(books);
        this.calculateTotalPriceUseCase = new CalculateTotalPriceUseCase(books);
        this.calculateAverageUnitPriceUseCase = new CalculateAverageUnitPriceForReferenceBooksUseCase(books);
        this.exportTextbooksUseCase = new ExportTextbooksByPublisherUseCase(books);
    }

    // Delegate to Use Case Classes
    public void addBook(Book book) {
        addBookUseCase.execute(book);
    }

    public boolean removeBook(String bookId) {
        return removeBookUseCase.execute(bookId);
    }

    public void updateBook(String bookId, Date newdateAdded, double newUnitPrice, int newQuantity, String newpublisher, String newCondition, double newTax) {
        updateBookUseCase.execute(bookId, newdateAdded, newUnitPrice, newQuantity,newpublisher, newCondition, newTax);
    }

    public List<Book> searchByPublisher(String publisher) {
        return searchByIDUseCase.execute(publisher);
    }

    public double calculateTotalPrice(Class<? extends Book> bookType) {
        return calculateTotalPriceUseCase.execute(bookType);
    }

    public double calculateAverageUnitPriceForReferenceBooks() {
        return calculateAverageUnitPriceUseCase.execute();
    }

    public List<Textbook> exportTextbooksByPublisher(String publisher) {
        return exportTextbooksUseCase.execute(publisher);
    }
    public List<Book> getBooks() {
        return books;
    }
}
