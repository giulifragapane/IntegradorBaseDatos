package org.example.dao;

import org.example.config.DatabaseConnection;
import org.example.models.Domicilio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DomicilioDaoImpl implements GenericDao<Domicilio> {


    @Override
    public void guardad(Domicilio entidad) {

        String sql = "INSERT INTO cliente (numero, calle, cp) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, entidad.getNumero());
            pstmt.setString(2, entidad.getCalle());
            pstmt.setInt(3, entidad.getCp());

            System.out.println("Se guardo correctamente");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void actualizar(Domicilio entidad) {

    }

    @Override
    public Domicilio filtrarId() {
        return null;
    }
}
