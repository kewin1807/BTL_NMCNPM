package javaCode.yourcart.model;

import javaCode.yourcart.beans.Category;
import javaCode.yourcart.beans.Product;
import javaCode.yourcart.utilize.FileUpload;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CategoryModel {
    Category bean = new Category();
    Connection con;
    DbConnection db = new DbConnection();
    public boolean addCategory(Category cat) {
        boolean b = false;
        try {
            con = db.openConnection();

            PreparedStatement pst1 = con.prepareStatement("INSERT into category (name)" + "values (?)");

            pst1.setString(1, cat.getName());
            System.out.println("before pst1");
            pst1.executeUpdate();
            pst1.close();
            b = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean updateCategory(Category updateCat) {
        try {
            int i = 0;
            con = db.openConnection();
            PreparedStatement pst = con.prepareStatement("update category set name=? where id=? ");
            pst.setString(1, updateCat.getName());
            pst.setInt(2, updateCat.getId());
            i = pst.executeUpdate();
            db.closeConnection();
            if (i > 0) {
                return true;
            }
        } catch (SQLException ex) {
            db.closeConnection();
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Category> AllCategories() {
        ArrayList<Category> AllCategory = new ArrayList<>();
        try {
            con = db.openConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * from category ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                AllCategory.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AllCategory;
    }
    
    public Category getCategory(int id) {

        Category category = new Category();
        try {
            con = db.openConnection();

            PreparedStatement pst = con.prepareStatement("SELECT * from category where id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }

    public boolean deleteCategory(int id) throws SQLException {
        int i = 0;
        con = db.openConnection();
        PreparedStatement product_pst = con.prepareStatement("select * from product where category_id=?");
        product_pst.setInt(1, id);
        ResultSet rs_pst = product_pst.executeQuery();
        while (rs_pst.next()) {
            int tmp = rs_pst.getInt("id");
            i++;
        }
        if(i==0){
            PreparedStatement pst = con.prepareStatement("delete from category where id=?");
            pst.setInt(1, id);
            i = pst.executeUpdate();
            db.closeConnection();
            if (i > 0) {
                return true;
            }
        }
        return false;
    }

}
