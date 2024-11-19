package book.totalbookprice;

import book.ui.totalbookprice.TotalBookPriceResultForm;

public class TotalBookPricePresenter implements TotalBookPriceOutputBoundary {
    private TotalBookPriceResultForm totalBookPriceResultForm;

    public TotalBookPricePresenter(TotalBookPriceResultForm totalBookPriceResultForm) {
        this.totalBookPriceResultForm = totalBookPriceResultForm;
    }

    @Override
    public void presentTotalBookPrice(TotalBookPriceResponseData responseData) {
        totalBookPriceResultForm.displayTotalPrice(responseData.getTotalPrice());
    }
}
