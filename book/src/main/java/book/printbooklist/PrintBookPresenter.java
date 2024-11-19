package book.printbooklist;

import java.util.List;

import book.ui.printbook.PrintBookResultForm;

public class PrintBookPresenter implements PrintBookOutputBoundary {
    private PrintBookResultForm printBookResultForm;

    public PrintBookPresenter(PrintBookResultForm printBookResultForm) {
        this.printBookResultForm = printBookResultForm;
    }

    @Override
    public void presentPrintBookResult(List<PrintBookResponseData> printBookResponseData) {
        // Trình bày kết quả qua giao diện
        printBookResultForm.display(printBookResponseData);
    }
}

