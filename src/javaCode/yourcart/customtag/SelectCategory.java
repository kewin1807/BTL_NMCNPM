package javaCode.yourcart.customtag;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javaCode.yourcart.beans.Category;
import javaCode.yourcart.model.CategoryModel;


//public class SelectCategory extends SimpleTagSupport {
//
//    private int selectID;
//
//    @Override
//    public void doTag() throws JspException {
//        JspWriter out = getJspContext().getOut();
//        try {
//            out.print("<a class=\"sidebar\">");
//            String selected = "";
//            //get all category
//            ArrayList<Category> categories = new CategoryModel().AllCategories();
//            for (Category category : categories) {
//                if(selectID == category.getId())
//                    selected = "class=\"active\"";
//                out.print("<a value='"+category.getId()+"' "+selected+">"+category.getName()+"</a>");
//                selected ="";
//            }
//            out.print("</div>");
//        } catch (java.io.IOException ex) {
//            throw new JspException("Error in Category tag", ex);
//        }
//    }
//    public void setSelectID(int selectID) {
//        this.selectID = selectID;
//    }
//}

public class SelectCategory extends SimpleTagSupport {

    private int selectID;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            out.print("<select name='category'>");
            String selected = "";
            //get all category
            ArrayList<Category> categories = new CategoryModel().AllCategories();
            for (Category category : categories) {
                if(selectID == category.getId())
                    selected = "selected";
                out.print("<option value='"+category.getId()+"' "+selected+">"+category.getName()+"</option>");
                selected ="";
            }
            out.print("</select>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Category tag", ex);
        }
    }

    public void setSelectID(int selectID) {
        this.selectID = selectID;
    }

}


