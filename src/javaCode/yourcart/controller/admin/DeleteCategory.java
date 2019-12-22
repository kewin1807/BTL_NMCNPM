package javaCode.yourcart.controller.admin;

import javaCode.yourcart.model.CategoryModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteCategory", urlPatterns = {"/admin/DeleteCategory"})
public class DeleteCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            if (new CategoryModel().deleteCategory(id)) {
                request.getSession().setAttribute("AlertMessage", "Category Deleted Successfully");
                request.getSession().setAttribute("AlertType", "success");
                response.sendRedirect("AdminCategoryServlet");

            } else {
                request.getSession().setAttribute("AlertMessage", "canot Delete category ..An Error occure");
                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminCategoryServlet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
