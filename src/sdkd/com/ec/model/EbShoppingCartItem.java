package sdkd.com.ec.model;

/**
 * Created by Administrator on 2016/7/9.
 */
public class EbShoppingCartItem {
    private EbProduct product;//商品对象
    private long quantity;//数量
    private double cost;//总金额

    public EbShoppingCartItem(EbProduct product, long quantity) {
        this.product = product;
        this.quantity = quantity;
        this.cost = product.getEpPrice() * quantity;
    }

    public double getCost() {
        return cost;
    }

    public EbProduct getProduct() {
        return product;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
        this.cost = product.getEpPrice() * quantity;
    }

    public long getQuantity() {
        return quantity;
    }
}
