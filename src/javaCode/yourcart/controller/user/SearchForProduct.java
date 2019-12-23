package javaCode.yourcart.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.model.ProductModel;

/**
 * search by name or price 
 * @author Osama & Nesmaa
 */
public class SearchForProduct extends HttpServlet {

    ArrayList<Product> products = new ArrayList<>();
    ProductModel model = new ProductModel();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("search") != null) {
            String search = request.getParameter("search");
            products = model.getProductBySearch(search);
        } else {
            int min = Integer.parseInt(request.getParameter("down"));
            System.out.println(min);
            int max = Integer.parseInt(request.getParameter("up"));
            System.out.println(max);

            products = model.getAllProductByPrice(min, max);
            System.out.println("sizeof product" + products.size());

        }

        request.setAttribute("allProducts", products);
        String nextJSP = "/shop.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
