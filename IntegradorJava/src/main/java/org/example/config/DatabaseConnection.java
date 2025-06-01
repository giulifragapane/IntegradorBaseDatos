package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static final String URL="jdbc:mysql://localhost:3306/integrador_java";
    public static final String USER="root";
    public static final String PASSWORD="";

    public static Connection getConnection() throws SQLException {
        if(URL== null || URL.isEmpty()||USER==null||USER.isEmpty()||PASSWORD==null){
            throw new SQLException("No se pudo conectar a la base de datos");
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
