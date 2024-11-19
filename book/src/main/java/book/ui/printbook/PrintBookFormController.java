package book.ui.printbook;

import book.printbooklist.PrintBookListInputBoundary;

public class PrintBookFormController {
    private PrintBookListInputBoundary printBookInputBoundary;

    public PrintBookFormController(PrintBookListInputBoundary printBookInputBoundary) {
        this.printBookInputBoundary = printBookInputBoundary;
    }

    public void printBook() {
        printBookInputBoundary.printBook();
    }
}

