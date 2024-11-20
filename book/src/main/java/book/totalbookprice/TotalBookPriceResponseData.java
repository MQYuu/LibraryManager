package book.totalbookprice;

public class TotalBookPriceResponseData {
    private double totalPrice;

    public TotalBookPriceResponseData(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    @Override
    public String toString() {
        return "TotalBookPriceResponseData{totalPrice=" + totalPrice + "}";
    }
}
