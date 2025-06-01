package org.example.dao;

import org.example.models.Domicilio;
import org.example.models.Persona;
import org.example.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoImpl implements GenericDao<Persona> {

    @Override
    public void guardar(Persona entity) throws SQLException {
        String query = "INSERT INTO persona (nombre, apellido, dni, fr_key_domicilio) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setString(1, entity.getNombre());
            stmn.setString(2, entity.getApellido());
            stmn.setInt(3, Integer.parseInt(entity.getDni()));
            stmn.setLong(4, entity.getDomicilio().getId());
            stmn.executeUpdate();
            System.out.println("Persona guardada");
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String query = "DELETE FROM persona WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setLong(1, id);
            stmn.executeUpdate();
            System.out.println("Persona eliminada");
        }
    }

    @Override
    public void actualizar(Persona entity) throws SQLException {
        String query = "UPDATE persona SET nombre = ?, apellido = ?, dni = ?, fr_key_domicilio = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setString(1, entity.getNombre());
            stmn.setString(2, entity.getApellido());
            stmn.setInt(3, Integer.parseInt(entity.getDni()));
            stmn.setLong(4, entity.getDomicilio().getId());
            stmn.setLong(5, entity.getId());
            stmn.executeUpdate();
            System.out.println("Persona actualizada");
        }
    }

    @Override
    public Persona buscar(Long id) throws SQLException {

        String query = "SELECT p.*, d.calle, d.numero, d.localidad, d.provincia FROM persona p JOIN domicilio d ON p.fr_key_domicilio = d.id WHERE persona.id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setLong(1, id);
            ResultSet rs = stmn.executeQuery();
            if (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        //rs.getLong("d.id"),
                        rs.getString("d.calle"),
                        rs.getInt("d.numero"),
                        rs.getString("d.localidad"),
                        rs.getString("d.provincia")

                );
                return new Persona(
                        //rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        domicilio
                );
            }
        }

        return null;
    }

    @Override
    public List<Persona> buscarTodos() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String query = "SELECT p.*, d.calle, d.numero, d.localidad, d.provincia FROM persona p JOIN domicilio d ON p.fr_key_domicilio = d.id";
        try(Connection conn = DatabaseConnection.getConnection();
            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery(query)) {

            while (rs.next()) {
                Persona persona = new Persona(
                        //rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        new Domicilio(
                                //rs.getLong("d.id"),
                                rs.getString("d.calle"),
                                rs.getInt("d.numero"),
                                rs.getString("d.localidad"),
                                rs.getString("d.provincia")
                        )
                );
                personas.add(persona);
            }

        }
        return personas;
    }
}





