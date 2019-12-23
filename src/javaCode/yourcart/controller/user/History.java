package javaCode.yourcart.controller.user;

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
import javaCode.yourcart.model.UserHistoryOrder;

@WebServlet(name = "History", urlPatterns = { "/History" })
public class History extends HttpServlet {
    CartModel cartModel;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("LoginUser");

        UserHistoryOrder historyOrder = new UserHistoryOrder();
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
             orders = historyOrder.getAllOrderByUser(user.getUserId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Order order:orders){
            System.out.println("date"+  order.getDate());
        }
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("userHistory.jsp").forward(request, response);
    }
}
