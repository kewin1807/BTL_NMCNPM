package javaCode.yourcart.controller.admin;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.model.ProductModel;

@WebServlet("/admin/AdminProductServlet")
public class AdminProductServlet extends HttpServlet {

    List<Product> allProducts = new ArrayList<Product>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        ProductModel productModel = new ProductModel();
//        allProducts = productModel.getAllProduct();
//        request.setAttribute("allProductsAdmin", allProducts);
//
//        String nextJSP = "/admin/products.jsp";
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//        dispatcher.forward(request, response);
        ProductModel productModel = new ProductModel();

        //-------------- handle paging ------------------
        int pageid = 1;
        int totalPerPage = 9;
        int start;

        if (request.getParameter("page") != null) {
            pageid = Integer.parseInt(request.getParameter("page"));
        }

        //end & start for paging
        start = (pageid - 1 )*totalPerPage;


        if (request.getParameter("cate") != null) {
            int cate = Integer.parseInt(request.getParameter("cate"));
            allProducts = productModel.getAllProductByCategoryId(cate,start,totalPerPage);
        } else {
            allProducts = productModel.getAllProduct(start, totalPerPage);
        }

        int noOfRecords = productModel.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / totalPerPage);

        request.setAttribute("allProductsAdmin", allProducts);
        request.setAttribute("noOfPagesAdmin", noOfPages);
        request.setAttribute("currentPageAdmin", pageid);

        String nextJSP = "/admin/products.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);

    }

}
