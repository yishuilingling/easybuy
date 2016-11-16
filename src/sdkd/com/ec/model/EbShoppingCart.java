package sdkd.com.ec.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class EbShoppingCart {
    private List<EbShoppingCartItem> items = new ArrayList<EbShoppingCartItem>();

    public void addItem(EbProduct product, long quantity) {
        items.add(new EbShoppingCartItem(product, quantity));
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public void modifyQuantity(int index, long quantity) {
        items.get(index).setQuantity(quantity);
    }

    public double getTotalCost() {
        double sum = 0;
        for (EbShoppingCartItem item : items) {
            sum = sum + item.getCost();
        }
        return sum;
    }

    public List<EbShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<EbShoppingCartItem> items) {
        this.items = items;
    }
}
