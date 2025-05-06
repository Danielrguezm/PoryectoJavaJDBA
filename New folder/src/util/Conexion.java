package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection conexion;

    private static final String URL = "jdbc:mysql://localhost:3306/proyectos_FP_2025";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Conexion() {}

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" Conexión establecida a la base de datos");
            } catch (SQLException e) {
                System.out.println(" Error de conexión: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println(" Conexión cerrada");
            } catch (SQLException e) {
                System.out.println(" Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
