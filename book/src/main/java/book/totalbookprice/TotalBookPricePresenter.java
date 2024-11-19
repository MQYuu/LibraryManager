package book.totalbookprice;

import book.ui.totalbookprice.TotalBookPriceResultForm;

public class TotalBookPricePresenter implements TotalBookPriceOutputBoundary {
    private TotalBookPriceResultForm resultForm;

    public TotalBookPricePresenter(TotalBookPriceResultForm resultForm) {
        this.resultForm = resultForm;
    }

    @Override
    public void presentTotalBookPrice(TotalBookPriceResponseData responseData) {
        resultForm.displayTotalPrice(responseData.getTotalPrice());
    }
}
