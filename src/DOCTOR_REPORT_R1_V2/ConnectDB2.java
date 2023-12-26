/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOCTOR_REPORT_R1_V2;

import java.sql.*;

/*import java.util.Properties;*/

//import javax.swing.*;
public class ConnectDB2 {

    public static Connection ConnectionDB() {

        String jdbcClassName = "com.ibm.jtopenlite.database.jdbc.JDBCDriver";
        Connection conn = null;

        try {
            Class.forName(jdbcClassName);
            conn = DriverManager.getConnection(LoginMain.LoginUrlConnection, LoginMain.LoginUsername, LoginMain.LoginPassword);
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    public static Connection ConnectionDBS() {

        String jdbcClassName = "com.ibm.jtopenlite.database.jdbc.JDBCDriver";
        Connection conn = null;

        try {
            Class.forName(jdbcClassName);
            conn = DriverManager.getConnection("jdbc:jtopenlite://192.200.9.190", "m3srvict", "ict12345");
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

}
