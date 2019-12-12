package javaCode.yourcart.model;

/**
 *
 * @author OsamaPC
 */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class DbConnection {

    Connection con = null;

    public DbConnection() {

    }

    public Connection openConnection() {
        try {


            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/yourcart");

            //connect to DB
            con = ds.getConnection();

            System.out.println(con);
            return con;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        catch (NamingException ex){
            Logger.getLogger("Loi");
        }
        return null;
    }

    public boolean closeConnection() {
        try {

            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}
