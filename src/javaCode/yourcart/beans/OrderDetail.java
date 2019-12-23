package javaCode.yourcart.beans;

import java.util.ArrayList;

public class OrderDetail {
    private Order order;
    private ArrayList<CartProduct>items;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<CartProduct> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartProduct> items) {
        this.items = items;
    }



}
