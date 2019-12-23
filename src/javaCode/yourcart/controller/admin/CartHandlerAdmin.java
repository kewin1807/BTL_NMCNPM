package javaCode.yourcart.controller.admin;


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


@WebServlet("/CartHandlerAdmin")
public class CartHandlerAdmin extends HttpServlet {
    CartModel cartModel;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("carts") != null) {
            cartModel = (CartModel) request.getSession().getAttribute("carts");
        } else {
            cartModel = new CartModel();
        }
        ArrayList<CartProduct> carts = cartModel.getProductFromCart();
        request.setAttribute("carts", carts);
        String nextJSP = "/admin/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }
}