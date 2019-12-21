package javaCode.yourcart.model;

import javaCode.yourcart.beans.Cart;
import javaCode.yourcart.beans.CartProduct;
import javaCode.yourcart.beans.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartModel extends DbConnection {

    ResultSet rs = null;

    Connection con;
    ArrayList<CartProduct> items;

    public CartModel() {
        items = new ArrayList<CartProduct>();
    }

    public ArrayList<CartProduct> getProductFromCart() {
        return items;
    }

    public void addCart(Product product, int quantity) {
        int product_id = product.getProductId();
        int index_product = search(product_id);
        if (index_product != -1) {
            editQantity(product_id, quantity);
        } else {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setQuantity(quantity);
            cartProduct.setName(product.getName());
            cartProduct.setPrice(product.getPrice());
            cartProduct.setPhoto(product.getPhoto());
            cartProduct.setDiscriptin(product.getDiscriptin());
            cartProduct.setProductId(product.getProductId());
            cartProduct.setQuantity_product(product.getQuantity());
            items.add(cartProduct);
        }
    }

    public void deleteCart(int cartID) {
        int index_cart = search(cartID);
        items.remove(index_cart);
    }

    public boolean deleteUserCart(int userID) {
        con = openConnection();
        PreparedStatement pst = null;
        try {
            //System.out.println("my con" + con);
            pst = con.prepareStatement("delete From cart where user_id=?");
            pst.setInt(1, userID);
            int executeUpdate = pst.executeUpdate();
            closeConnection();
            if (executeUpdate > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private int search(int pID) {
        int qu = -1;
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getProductId() == pID) {
                    return i;
                }
            }
        }
        return qu;
    }

    private void editQantity(int productID, int quantity) {
        int index_product = search(productID);
        CartProduct cartProduct = items.get(index_product);
        cartProduct.setQuantity(quantity);
        items.set(index_product, cartProduct);
    }

    public int getNubmberOfCartsForUser() {
        if(items.size() == 0){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            count += items.get(i).getQuantity();
            System.out.println("size ==" + items.size());
        }
        System.out.println("Quantity ==  " + count);
        return count;
    }
    public void reduceQuantity(int product_id) {
        int index_product = search(product_id);
        if (index_product != -1) {
            CartProduct cartProduct = items.get(index_product);
            int quantity = cartProduct.getQuantity();
            if (quantity < 2) {
                deleteCart(product_id);
            } else {
                cartProduct.setQuantity(quantity - 1);
                items.set(index_product, cartProduct);
            }
        }
    }

    public void increaseQuantity(int product_id) {
        int index_product = search(product_id);
        if (index_product != -1) {
            CartProduct cartProduct = items.get(index_product);
            int quantity = cartProduct.getQuantity();
            cartProduct.setQuantity(quantity + 1);
            items.set(index_product, cartProduct);
        }
    }
}
