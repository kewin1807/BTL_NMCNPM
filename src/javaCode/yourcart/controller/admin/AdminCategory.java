
package javaCode.yourcart.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javaCode.yourcart.beans.Category;
import javaCode.yourcart.model.CategoryModel;

/**
 * add category
 * @author Nesmaa
 */
@WebServlet("/admin/AdminCategory")
public class AdminCategory extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = new CategoryModel().getCategory(id);
        if(category == null){
            request.getSession().setAttribute("message", "Category not found");
            response.sendRedirect("../Failed.jsp");
        } else {
            request.setAttribute("category", category);
            request.setAttribute("type", "Edit");
            request.getRequestDispatcher("/admin/addcategory.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // int id = Integer.parseInt(request.getParameter("CategoryId"));
        String name = request.getParameter("CategoryName");
        /// object from category 
        
        
        Category category = new Category();
        
        category.setName(name);

        if(request.getParameter("id") != null && !request.getParameter("id").trim().equals("")){
            int id = Integer.parseInt(request.getParameter("id"));
            category.setId(id);

            if (new CategoryModel().updateCategory(category)) {
                System.out.println("updateCategory");
                //redirect to Success
                //set alert message
                request.getSession().setAttribute("AlertMessage", "Category Updated Successfully");
                //set alert type
                request.getSession().setAttribute("AlertType", "success");
                response.sendRedirect("AdminCategoryServlet");
                return;
            } else {
                //set alert message
                request.getSession().setAttribute("AlertMessage", "An Error occurs, please make it short");
                //set alert type
                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminCategoryServlet");
                return;
            }
        }else{
            System.out.println("addCategory");
            if (new CategoryModel().addCategory(category))
            {
                //set alert message
                request.getSession().setAttribute("AlertMessage", "Category Added Successfully");
                //set alert type
                request.getSession().setAttribute("AlertType", "success");
                response.sendRedirect("AdminCategoryServlet");
            }
            else
            {
                //set alert message
                request.getSession().setAttribute("AlertMessage", "An Error occurs, please make it short");
                //set alert type
                request.getSession().setAttribute("AlertType", "danger");
                response.sendRedirect("AdminCategoryServlet");
            }
        }

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
