package javaCode.yourcart.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javaCode.yourcart.model.CartModel;
import javaCode.yourcart.beans.User;
import javaCode.yourcart.beans.CartProduct;


@WebServlet("/CartHandlerServlet")
public class CartHandlerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CartModel cartModel = (CartModel) request.getSession().getAttribute("carts");
        ArrayList<CartProduct> carts = cartModel.getProductFromCart();
        request.setAttribute("carts", carts);
        String nextJSP = "/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }
}
