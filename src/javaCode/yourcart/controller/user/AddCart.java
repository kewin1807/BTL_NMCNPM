
package javaCode.yourcart.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaCode.yourcart.beans.Cart;
import javaCode.yourcart.beans.User;
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.model.ProductModel;

import javaCode.yourcart.model.CartModel;

@WebServlet("/addCart")
public class AddCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CartModel cartModel = new CartModel();
        if (request.getSession().getAttribute("carts") != null) {
            cartModel = (CartModel) request.getSession().getAttribute("carts");
        }
        int pID = Integer.parseInt(request.getParameter("productID"));
        ProductModel productModel = new ProductModel();
        Product product = productModel.getProduct(pID);
        int quantity = Integer.parseInt(request.getParameter("qaunty"));
        cartModel.addCart(product, quantity);
        response.getWriter().print(cartModel.getNubmberOfCartsForUser());
        HttpSession session = request.getSession(true);
        session.setAttribute("carts", cartModel);
        session.setMaxInactiveInterval(60 * 60 * 48);
    }

}
