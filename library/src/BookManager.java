import java.util.ArrayList;
import java.util.List;

import bookdata.Book;
import bookdata.Textbook;
import usecase.AddBookUseCase;
import usecase.RemoveBookUseCase;
import usecase.UpdateBookUseCase;
import usecase.SearchByPublisherUseCase;
import usecase.CalculateTotalPriceUseCase;
import usecase.CalculateAverageUnitPriceForReferenceBooksUseCase;
import usecase.ExportTextbooksByPublisherUseCase;

class BookManager {
    private List<Book> books = new ArrayList<>();

    private AddBookUseCase addBookUseCase;
    private RemoveBookUseCase removeBookUseCase;
    private UpdateBookUseCase updateBookUseCase;
    private SearchByPublisherUseCase searchByPublisherUseCase;
    private CalculateTotalPriceUseCase calculateTotalPriceUseCase;
    private CalculateAverageUnitPriceForReferenceBooksUseCase calculateAverageUnitPriceUseCase;
    private ExportTextbooksByPublisherUseCase exportTextbooksUseCase;

    public BookManager() {
        this.addBookUseCase = new AddBookUseCase(books);
        this.removeBookUseCase = new RemoveBookUseCase(books);
        this.updateBookUseCase = new UpdateBookUseCase(books);
        this.searchByPublisherUseCase = new SearchByPublisherUseCase(books);
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

    public void updateBook(String bookId, double newUnitPrice, int newQuantity) {
        updateBookUseCase.execute(bookId, newUnitPrice, newQuantity);
    }

    public List<Book> searchByPublisher(String publisher) {
        return searchByPublisherUseCase.execute(publisher);
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
}
