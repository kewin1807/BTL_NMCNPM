package javaCode.yourcart.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaCode.yourcart.beans.User;
import javaCode.yourcart.model.CartModel;

/**
 *
 * @author OsamaPC
 */
@WebServlet("/ReduceQuantity")
public class ReduceQuantity extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartModel cartModel;
        if(request.getSession().getAttribute("carts") != null){
            cartModel = (CartModel) request.getSession().getAttribute("carts");
        }
        else{
            cartModel = new CartModel();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        cartModel.reduceQuantity(id);
        response.getWriter().print(cartModel.getNubmberOfCartsForUser());
        HttpSession session = request.getSession(true);
        session.setAttribute("carts", cartModel);
    }
}
