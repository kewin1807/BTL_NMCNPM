package javaCode.yourcart.controller.user;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

@WebServlet(name = "Pay", urlPatterns = {"/Pay"})
public class Pay extends HttpServlet {

    CartModel cartModel;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher("/order.jsp").forward(request, response);
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("carts") != null) {
            cartModel = (CartModel) request.getSession().getAttribute("carts");
        } else {
            cartModel = new CartModel();
        }
        double total = 0;
        String message = "";

        //get user form session
        User user = (User) request.getSession().getAttribute("LoginUser");

        //get product of user
        ArrayList<CartProduct> productCart = cartModel.getProductFromCart();
//        String address = request.getParameter("adr");
//        String province = request.getParameter("province");
//        String district = request.getParameter("district");
        String addressOrder = request.getParameter("address");
        Order order = new Order();
        order.setAddress(addressOrder);
        order.setUser_id(user.getUserId());
        order.setStatus_id(0);
        order.setDate(LocalDateTime.of(LocalDate.now(), LocalTime.now())+"");
        Payment payment = new Payment();
        boolean checkOrder = payment.startPayment(user, productCart, order);
        cartModel.deleteUserCart();
        if (checkOrder) {
            message = "Thanks for buying from YourCart ^_^ <br/>"
                    + "your product will delivered in two days ..";
            request.getSession().setAttribute("message", message);
            response.sendRedirect("Success.jsp");
        } else {
            request.getSession().setAttribute("message", "Error in proccess please try agine later :( ");
            response.sendRedirect("Failed.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return "pay money for product of user";
    }

}
