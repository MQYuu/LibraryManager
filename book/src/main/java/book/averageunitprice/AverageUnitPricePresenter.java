package book.averageunitprice;

import book.ui.averageunitprice.AverageUnitPriceResultForm;

public class AverageUnitPricePresenter implements AverageUnitPriceOutputBoundary {
    private AverageUnitPriceResultForm resultForm;

    public AverageUnitPricePresenter(AverageUnitPriceResultForm resultForm) {
        this.resultForm = resultForm;
    }

    @Override
    public void presentAverageUnitPriceResult(AverageUnitPriceResponseData responseData) {
        resultForm.display(responseData);
    }
}
