package book.usecase;

import book.addbook.AddBookInputBoundary;
import book.addbook.AddBookOutputBoundary;
import book.addbook.AddBookRequestData;
import book.addbook.AddBookResponseData;
import book.database.BookRepository;
import book.entities.Book;
import book.entities.ReferenceBook;
import book.entities.TextBook;

public class AddBookService implements AddBookInputBoundary {
    private BookRepository bookRepository;
    private AddBookOutputBoundary addBookOutputBoundary;

    public AddBookService(BookRepository bookRepository, AddBookOutputBoundary addBookOutputBoundary) {
        this.bookRepository = bookRepository;
        this.addBookOutputBoundary = addBookOutputBoundary;
    }

    @Override
    public void executeAdd(AddBookRequestData requestData) {
        Book book;
        if ("TextBook".equals(requestData.getType())) {
            book = new TextBook(requestData.getBookId(), requestData.getEntryDate(), requestData.getUnitPrice(), requestData.getQuantity(), requestData.getPublisher(), requestData.getCondition());
        } else {
            book = new ReferenceBook(requestData.getBookId(), requestData.getEntryDate(), requestData.getUnitPrice(), requestData.getQuantity(), requestData.getPublisher(), requestData.getTax());
        }
        
        bookRepository.addBook(book);
        AddBookResponseData responseData = new AddBookResponseData("Book added successfully.");
        addBookOutputBoundary.presentResult(responseData);
    }
}

