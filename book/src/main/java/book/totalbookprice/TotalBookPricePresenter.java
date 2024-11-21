package book.totalbookprice;

import book.ui.totalbookprice.TotalBookPriceResultForm;

public class TotalBookPricePresenter implements TotalBookPriceOutputBoundary {
    private TotalBookPriceResultForm totalBookPriceResultForm;

    public TotalBookPricePresenter(TotalBookPriceResultForm totalBookPriceResultForm) {
        this.totalBookPriceResultForm = totalBookPriceResultForm;
    }

    @Override
    public void presentTotalBookPrice(TotalBookPriceResponseData totalBookPriceResponseData) {
        totalBookPriceResultForm.displayTotalPrice(totalBookPriceResponseData.getTotalPrice());
    }
}
