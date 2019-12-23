package javaCode.yourcart.controller.admin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaCode.yourcart.beans.*;
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.model.CartModel;
import javaCode.yourcart.model.UserHistory;
import javaCode.yourcart.model.UserHistoryOrder;
@WebServlet(name = "DetailAdminOrder", urlPatterns = { "/admin/DetailAdminOrder" })

public class DetailAdminOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserHistoryOrder historyOrder = new UserHistoryOrder();
        ArrayList<CartProduct> products = new ArrayList<CartProduct>();
        OrderUser orderUser = new OrderUser();

        int order_id = Integer.parseInt(request.getParameter("id"));
        try {
            orderUser = historyOrder.getInfoOrderUser(order_id);
            products = historyOrder.getDetailOrderProduct(order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(CartProduct cart: products){
            System.out.println("product_id" + cart.getProductId());
        }
        request.setAttribute("order", orderUser);
        request.setAttribute("products", products);

        request.getRequestDispatcher("/admin/orderDetail.jsp").forward(request, response);
    }
}
