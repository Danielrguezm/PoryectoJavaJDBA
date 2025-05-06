package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static void main(String[] args) {

        String urlXampp = "jdbc:mysql://localhost:3306/proyectos_FP_2025";
        String user = "root";
        String passwordXampp = "";

        try {
            Connection conn = DriverManager.getConnection(urlXampp, user, passwordXampp);
            System.out.println("CONEXION ON");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CONEXION OFF");
        }
    }
}