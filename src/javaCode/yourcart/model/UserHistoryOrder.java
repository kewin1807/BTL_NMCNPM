
package javaCode.yourcart.model;


import javaCode.yourcart.beans.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserHistoryOrder extends DbConnection {

    ResultSet rs = null;
    // DbConnection db = new DbConnection();
    Connection con;

    public int addUserOrder(Order o) throws SQLException {
        int order_id = -1;
        try{
            con = openConnection();
            PreparedStatement pst = null;
            pst = con.prepareStatement("insert into orders (user_id,status_id,address)Values (?,?,?)");
            pst.setInt(1, o.getUser_id());
            pst.setInt(2, o.getStatus_id());
            pst.setString(3, o.getAddress());
            pst.executeUpdate();
            PreparedStatement pstMax = null;
            pstMax = con.prepareStatement("SELECT MAX(id) from orders");
            ResultSet rs = pstMax.executeQuery();
            while (rs.next()){
                System.out.println("order_id" + rs.getInt(1));
                order_id = rs.getInt(1);
                return order_id;
            }
        }
        catch (SQLException ex){
            closeConnection();
            ex.printStackTrace();
            return -1;
        }
        finally {
            closeConnection();
        }
        return order_id;
    }
}