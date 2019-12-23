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
@WebServlet(name = "UpdateOrder", urlPatterns = {"/admin/UpdateOrder"})

public class UpdateOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserHistoryOrder historyOrder = new UserHistoryOrder();
        ArrayList<CartProduct> carts = new ArrayList<CartProduct>();
        int status_id = Integer.parseInt(request.getParameter("status_id"));
        int order_id = Integer.parseInt(request.getParameter("order_id"));

        if(request.getSession().getAttribute("productOrders") != null){
            carts = (ArrayList<CartProduct>) request.getSession().getAttribute("productOrders");
        }
        boolean update = false;
        try {
            update = historyOrder.updateOrder(carts, status_id,order_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(update){
            request.getSession().setAttribute("AlertMessage", "Order Updated Successfully");
            request.getSession().setAttribute("AlertType", "success");
        }
        else{
            request.getSession().setAttribute("AlertMessage", "canot Update product ..An Error occure");
            //set alert type
            request.getSession().setAttribute("AlertType", "danger");
        }
        request.getRequestDispatcher("/admin/orderDetail.jsp").forward(request, response);
    }
}
