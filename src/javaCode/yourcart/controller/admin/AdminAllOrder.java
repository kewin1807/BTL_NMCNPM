package javaCode.yourcart.controller.admin;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaCode.yourcart.beans.*;
import javaCode.yourcart.model.UserHistoryOrder;

@WebServlet(name = "AdminAllOrder", urlPatterns = { "/admin/AdminAllOrder" })

public class AdminAllOrder extends HttpServlet {
    ArrayList<OrderUser>  orders = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        UserHistoryOrder userHistoryOrder = new UserHistoryOrder();
//        try {
//            orders = userHistoryOrder.getAllOrder();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("/admin/allOrder.jsp").forward(request, response);

        UserHistoryOrder orderModel = new UserHistoryOrder();

        //-------------- handle paging ------------------
        int pageid = 1;
        int totalPerPage = 5;
        int start;

        if (request.getParameter("page") != null) {
            pageid = Integer.parseInt(request.getParameter("page"));
        }

        //end & start for paging
        start = (pageid - 1 )*totalPerPage;
        try {
            orders = orderModel.getAllOrder(start, totalPerPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int noOfRecords = orderModel.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / totalPerPage);

        request.setAttribute("orders", orders);

        System.out.println("noOfPagesOrder");
        System.out.println(noOfPages);
        System.out.println("currentPageOrder");
        System.out.println(pageid);

        request.setAttribute("noOfPagesOrder", noOfPages);
        request.setAttribute("currentPageOrder", pageid);

        String nextJSP = "/admin/allOrder.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }
}
