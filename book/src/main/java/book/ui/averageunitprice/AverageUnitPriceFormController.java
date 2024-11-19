package book.ui.averageunitprice;

import book.averageunitprice.AverageUnitPriceInputBoundary;

public class AverageUnitPriceFormController {
    private AverageUnitPriceInputBoundary inputBoundary;

    public AverageUnitPriceFormController(AverageUnitPriceInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void calculateAverageUnitPrice() {
        inputBoundary.calculateAverageUnitPrice();
    }
}
