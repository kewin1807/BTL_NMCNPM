package javaCode.yourcart.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaCode.yourcart.beans.CartProduct;
import javaCode.yourcart.beans.User;
import javaCode.yourcart.beans.Order;
import javaCode.yourcart.model.CartModel;
import javaCode.yourcart.model.Payment;
import javaCode.yourcart.model.UserHistory;
import javaCode.yourcart.model.UserHistoryOrder;
@WebServlet(name = "Checkout", urlPatterns = {"/Checkout"})
public class Checkout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("order.jsp");
    }
}
