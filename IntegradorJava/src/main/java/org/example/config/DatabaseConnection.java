package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL="jdbc:mysql://localhost:3306/integrador_java";
    private static final String USER="root";
    private static final String PASSWORD="";
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: Driver JDBC no hallado ",e);
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Conectando a la base de datos...");
        if(URL== null || URL.isEmpty()||USER==null||USER.isEmpty()||PASSWORD==null){
            throw new SQLException("No se pudo conectar a la base de datos");
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if(connection!=null){
            connection.close();
        }else {
            throw new SQLException("No se pudo cerrar la conexion");
        }
    }
}