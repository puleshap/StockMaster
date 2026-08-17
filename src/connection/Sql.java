/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pulesh
 */
public class Sql {

  
    private static Connection connection;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_NAME = "stockmaster";

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ExceptionInInitializerError("Oops! MySQL Connection Failed...!");
        }
    }

    public static void iud(String query) {
        try {
            getConnection().createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet search(String query) {
        try {
            return getConnection().createStatement().executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
