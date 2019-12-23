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
@WebServlet(name = "AdminAllOrder", urlPatterns = { "/admin/AdminAllOrder" })

public class AdminAllOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserHistoryOrder userHistoryOrder = new UserHistoryOrder();
        ArrayList<OrderUser>  orders = new ArrayList<OrderUser>();
        try {
            orders = userHistoryOrder.getAllOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/admin/allOrder.jsp").forward(request, response);
    }
}
