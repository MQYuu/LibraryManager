package book.ui.averageunitprice;

import book.averageunitprice.AverageUnitPriceInputBoundary;

public class AverageUnitPriceFormController {
    private AverageUnitPriceInputBoundary averageUnitPriceInputBoundary;

    public AverageUnitPriceFormController(AverageUnitPriceInputBoundary averageUnitPriceInputBoundary) {
        this.averageUnitPriceInputBoundary = averageUnitPriceInputBoundary;
    }

    public void calculateAverageUnitPrice() {
        averageUnitPriceInputBoundary.calculateAverageUnitPrice();
    }
}
