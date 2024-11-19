package book.ui.totalbookprice;

import book.totalbookprice.TotalBookPriceInputBoundary;

public class TotalBookPriceFormController {
    private TotalBookPriceInputBoundary inputBoundary;

    public TotalBookPriceFormController(TotalBookPriceInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void calculateTotalBookPrice() {
        inputBoundary.calculateTotalBookPrice();
    }
}
