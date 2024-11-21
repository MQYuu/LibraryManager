package book.averageunitprice;

import book.ui.averageunitprice.AverageUnitPriceResultForm;

public class AverageUnitPricePresenter implements AverageUnitPriceOutputBoundary {
    private AverageUnitPriceResultForm averageUnitPriceResultForm;

    public AverageUnitPricePresenter(AverageUnitPriceResultForm averageUnitPriceResultForm) {
        this.averageUnitPriceResultForm = averageUnitPriceResultForm;
    }

    @Override
    public void presentAverageUnitPriceResult(AverageUnitPriceResponseData averageUnitPriceResponseData) {
        averageUnitPriceResultForm.display(averageUnitPriceResponseData);
    }
}
