package proyect.packages.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=itamdb;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "a20201234";  //Colocar segun el user de su sql
    private static final String PASSWORD = "1234"; //Colocar segun el passwor de su sql

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return connection;
    }
}