/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polaczenie;

import java.sql.*;


/**
 *
 * @author Mateusz
 */
public class PolaczenieDB {

    private static Connection con = null;

    public static Connection getConnection() {
        if (con != null) {
            return con;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rezerwacja_pokoi", "root", "root");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
