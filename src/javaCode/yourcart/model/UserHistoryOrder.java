
package javaCode.yourcart.model;


import javaCode.yourcart.beans.CartProduct;
import javaCode.yourcart.beans.Order;
import javaCode.yourcart.beans.OrderDetail;
import javaCode.yourcart.beans.OrderUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserHistoryOrder extends DbConnection {

    ResultSet rs = null;
    // DbConnection db = new DbConnection();
    Connection con;
    private int noOfRecords ;

    public int addUserOrder(Order o) {
        int order_id = -1;
        try {
            con = openConnection();
            PreparedStatement pst = null;
            pst = con.prepareStatement("insert into orders (user_id,status_id,address,date)Values (?,?,?,?)");
            pst.setInt(1, o.getUser_id());
            pst.setInt(2, o.getStatus_id());
            pst.setString(3, o.getAddress());
            pst.setString(4, o.getDate());
            pst.executeUpdate();
            PreparedStatement pstMax = null;
            pstMax = con.prepareStatement("SELECT MAX(id) from orders");
            ResultSet rs = pstMax.executeQuery();
            while (rs.next()) {
                System.out.println("order_id" + rs.getInt(1));
                order_id = rs.getInt(1);
                return order_id;
            }
        } catch (SQLException ex) {
            closeConnection();
            ex.printStackTrace();
            return -1;
        } finally {
            closeConnection();
        }
        return order_id;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public ArrayList<OrderUser> getAllOrder(int start , int limit) throws SQLException {
        ArrayList<OrderUser> orders = new ArrayList<OrderUser>();
        con = openConnection();
        PreparedStatement pst = con.prepareStatement("select orders.id, orders.date,orders.address,orders.status_id,users.photo,users.id,users.username from orders join users on orders.user_id = users.id order by orders.id desc limit ?, ?");
        pst.setInt(1, start);
        pst.setInt(2, limit);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            OrderUser order = new OrderUser();
            order.setOrder_id(rs.getInt(1));
            order.setDate(rs.getString(2));
            order.setAddress(rs.getString(3));
            order.setStatus_id(rs.getInt(4));
            order.setUser_photo(rs.getString(5));
            order.setUser_id(rs.getInt(6));
            order.setUser_name(rs.getString(7));
            orders.add(order);
        }
        rs = con.prepareStatement("SELECT count(*) FROM orders").executeQuery();
        if(rs.next()){
            this.noOfRecords = rs.getInt(1);
        }
        closeConnection();
        return orders;
//        ArrayList<Order> orders = new ArrayList();
//        try {
//            con = db.openConnection();
//            pst = con.prepareStatement("select * from product ORDER BY id DESC LIMIT ? , ?");
//            pst.setInt(1, start);
//            pst.setInt(2, limit);
//            Product p;
//            rs = pst.executeQuery();
//
//            while (rs.next()) {
//                p = new Product(rs.getString("name"), rs.getDouble("price"),
//                        rs.getString("model"), rs.getString("date"), rs.getString("photo"),
//                        rs.getString("descriptin"), rs.getInt("quantity"), rs.getInt("id"),
//                        rs.getInt("category_id"));
//                list.add(p);
//
//            }
//
//            //get number of record in DB
//            rs = con.prepareStatement("SELECT count(*) FROM product").executeQuery();
//            if(rs.next()){
//                this.noOfRecords = rs.getInt(1);
//            }
//
//
//        } catch (SQLException ex) {
//            db.closeConnection();
//            ex.printStackTrace();
//        }
//        System.out.println(list.size());
//        return list;
    }

    public ArrayList<Order> getAllOrderByUser(int user_id) throws SQLException {
        ArrayList<Order> orders = new ArrayList<Order>();
        con = openConnection();
        PreparedStatement pst = null;
        pst = con.prepareStatement("select * from orders where user_id=?");
        pst.setInt(1, user_id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setOrder_id(rs.getInt(1));
            order.setUser_id(rs.getInt(2));
            order.setStatus_id(rs.getInt(3));
            order.setAddress(rs.getString(4));
            order.setDate(rs.getString(5));
            orders.add(order);
        }
        closeConnection();
        return orders;
    }

    public OrderUser getInfoOrderUser(int order_id) throws SQLException {
        OrderUser orderUser = new OrderUser();
        con = openConnection();
        PreparedStatement pst = null;
        pst = con.prepareStatement("select orders.id, orders.address, orders.date,users.id,users.photo,users.username from orders, users where orders.id=? and orders.user_id=users.id");
        pst.setInt(1, order_id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            orderUser.setOrder_id(order_id);
            orderUser.setAddress(rs.getString(2));
            orderUser.setDate(rs.getString(3));
            orderUser.setUser_id(rs.getInt(4));
            orderUser.setUser_photo(rs.getString(5));
            orderUser.setUser_name(rs.getString(6));
        }
        closeConnection();
        return orderUser;
    }

    public ArrayList<CartProduct> getDetailOrderProduct(int order_id) throws SQLException {
        OrderDetail orders = new OrderDetail();
        con = openConnection();
        PreparedStatement pstProducts = null;
        pstProducts = con.prepareStatement("select p.id, p.name, p.price, p.quantity,p.model,p.photo,h.date,h.quantity from product as p, history AS h where h.order_id=? and h.product_id=p.id");
        pstProducts.setInt(1, order_id);
        ResultSet rs = pstProducts.executeQuery();
        ArrayList<CartProduct> products = new ArrayList<CartProduct>();
        while (rs.next()) {
            CartProduct cartProduct = new CartProduct();
            System.out.println(rs.getInt(1));
            cartProduct.setProductId(rs.getInt(1));
            cartProduct.setName(rs.getString(2));
            cartProduct.setPrice(rs.getDouble(3));
            cartProduct.setQuantity_product(rs.getInt(4));
            cartProduct.setModel(rs.getString(5));
            cartProduct.setPhoto(rs.getString(6));
            cartProduct.setDate(rs.getString(7));
            cartProduct.setQuantity(rs.getInt(8));
            products.add(cartProduct);
        }
        closeConnection();
        return products;
    }
    public void updateProductOrder(ArrayList<CartProduct> carts) throws SQLException {
        con = openConnection();
        PreparedStatement pstProduct = null;
        pstProduct = con.prepareStatement("UPDATE product set quantity=quantity+?,sold=sold-? where id=?");
        for (CartProduct cartProduct : carts) {
            pstProduct.setInt(1, cartProduct.getQuantity());
            pstProduct.setInt(2, cartProduct.getQuantity());
            pstProduct.setInt(3, cartProduct.getProductId());
            pstProduct.executeQuery();
        }
        closeConnection();

    }
    public boolean updateOrder(ArrayList<CartProduct> carts, int status_id, int order_id) throws SQLException {
        con = openConnection();
        PreparedStatement pst = null;
        if(status_id == 0){
            return true;
        }
        else if(status_id == 2){
            pst = con.prepareStatement("update orders set status_id=? where orders.id=?");
            pst.setInt(1, status_id);
            pst.setInt(2, order_id);
            int executeUpdate = pst.executeUpdate();
            closeConnection();
            if(executeUpdate > 0){
                return true;
            }
        }
        else{
            pst = con.prepareStatement("update orders set status_id=? where orders.id=?");
            pst.setInt(1, status_id);
            pst.setInt(2, order_id);
            int executeUpdate = pst.executeUpdate();
            closeConnection();
            if(executeUpdate > 0){
                updateProductOrder(carts);
                return true;
            }
        }
        return false;
    }

}