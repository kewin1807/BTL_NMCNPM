
package javaCode.yourcart.model;

import javaCode.yourcart.beans.CartProduct;
import javaCode.yourcart.beans.History;
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.beans.User;
import javaCode.yourcart.beans.Order;
import javaCode.yourcart.model.UserHistoryOrder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Payment extends DbConnection{
    public Payment() {
        
        try {
            openConnection(); //open conncetion on DB

            //disable auto commit
            con.setAutoCommit(false);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public boolean startPayment(User user, ArrayList<CartProduct> sold, Order order){
        try {
            ProductModel productModel = new ProductModel();
            UserHistoryOrder historyOrder = new UserHistoryOrder();
            int order_id = historyOrder.addUserOrder(order);
            for (CartProduct itemSold : sold) {

                Product product = new Product();
                product.setProductId(itemSold.getProductId());
                product.setQuantity(itemSold.getQuantity_product() - itemSold.getQuantity());
                product.setSold(itemSold.getQuantity());

                productModel.updateProductQauntity(product);
                
                //save proccess in history 
                History history = new History();
                history.setData(LocalDateTime.of(LocalDate.now(), LocalTime.now())+"");
                history.setProductId(itemSold.getProductId());
                history.setUserId(user.getUserId());
                history.setQuantity(itemSold.getQuantity());

                new UserHistory().addUserHistory(history, order_id);
            }

            //empty user cart
         
            //commit 
            con.commit();
            return true;
            //return defualt value of Database


        } catch (SQLException ex) {
            System.out.println("----Error in Transaction ----");
            try {
                //rollback
                con.rollback();
                con.setAutoCommit(true);
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                return false;
            }
            ex.printStackTrace();
        }
        return false;
    }
}
