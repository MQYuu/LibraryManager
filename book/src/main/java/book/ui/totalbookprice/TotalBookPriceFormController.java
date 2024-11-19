package book.ui.totalbookprice;

import book.totalbookprice.TotalBookPriceInputBoundary;

public class TotalBookPriceFormController {
    private TotalBookPriceInputBoundary totalBookPriceInputBoundary;

    public TotalBookPriceFormController(TotalBookPriceInputBoundary totalBookPriceInputBoundary) {
        this.totalBookPriceInputBoundary = totalBookPriceInputBoundary;
    }

    public void calculateTotalBookPrice() {
        totalBookPriceInputBoundary.calculateTotalBookPrice();
    }
}
