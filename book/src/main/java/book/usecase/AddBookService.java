package book.usecase;

import book.addbookinterface.AddBookInputBoundary;
import book.addbookinterface.AddBookOutputBoundary;
import book.addbookinterface.AddBookRequestData;
import book.addbookinterface.AddBookResponseData;
import book.entities.AddBook;
import book.entities.ReferenceBook;
import book.entities.TextBook;

public class AddBookService implements AddBookInputBoundary {
    private BookRepository bookRepository;
    private AddBookOutputBoundary outputBoundary;

    public AddBookService(BookRepository bookRepository, AddBookOutputBoundary outputBoundary) {
        this.bookRepository = bookRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void executeAdd(AddBookRequestData requestData) {
        AddBook book;
        if ("TextBook".equals(requestData.getType())) {
            book = new TextBook(requestData.getBookId(), requestData.getEntryDate(), requestData.getUnitPrice(), requestData.getQuantity(), requestData.getPublisher(), requestData.getCondition());
        } else {
            book = new ReferenceBook(requestData.getBookId(), requestData.getEntryDate(), requestData.getUnitPrice(), requestData.getQuantity(), requestData.getPublisher(), requestData.getTax());
        }
        
        bookRepository.addBook(book);
        AddBookResponseData responseData = new AddBookResponseData("Book added successfully.");
        outputBoundary.presentResult(responseData);
    }
}

