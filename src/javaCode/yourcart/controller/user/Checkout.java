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
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.model.CartModel;
import javaCode.yourcart.model.ProductModel;

@WebServlet(name = "Checkout", urlPatterns = { "/Checkout" })
public class Checkout extends HttpServlet {
    CartModel cartModel;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";
        if (request.getSession().getAttribute("carts") != null) {
            cartModel = (CartModel) request.getSession().getAttribute("carts");
        } else {
            cartModel = new CartModel();
        }
        ProductModel productModel = new ProductModel();
        for (CartProduct cartProduct : cartModel.getProductFromCart()) {
            Product product = productModel.getProduct(cartProduct.getProductId());
            if (cartProduct.getQuantity() > product.getQuantity()) {
                message = "ProductId " + cartProduct.getProductId() + " order exceed quantity";
                request.getSession().setAttribute("message", message);
                response.sendRedirect("Failed.jsp");
                // request.getRequestDispatcher("checkout.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect("order.jsp");
    }
}
