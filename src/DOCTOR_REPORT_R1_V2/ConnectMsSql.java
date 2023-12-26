package DOCTOR_REPORT_R1_V2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bangkokranch
 */
import java.sql.*;
import javax.swing.*;

public class ConnectMsSql {

    Connection conn = null;

    public static Connection ConnectionDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connstring = "jdbc:sqlserver://192.200.9.182:1433;user=sa;password=sa;database=BRLAS400";
            //String connstring = "jdbc:sqlserver://192.200.9.182:1433;database=BRLAS400";    
            Connection conn = DriverManager.getConnection(connstring);
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

}
