package javaCode.yourcart.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javaCode.yourcart.model.CartModel;

/**
 * get number of product in user cart
 * 
 * @author OsamaPC
 */
@WebServlet("/getCartCount")
public class NumberOFCartsForUser extends HttpServlet {

    CartModel cartModel;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getSession().getAttribute("carts")!= null){
            cartModel = (CartModel) request.getSession().getAttribute("carts");
        }else{
            cartModel = new CartModel();
        }
        int addCart = cartModel.getNubmberOfCartsForUser();
        System.out.println("addCart: "+ addCart);
        response.getWriter().print(addCart);
    }

}
