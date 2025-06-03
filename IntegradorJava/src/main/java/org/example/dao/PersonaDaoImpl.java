package org.example.dao;

import org.example.models.Domicilio;
import org.example.models.Persona;
import org.example.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoImpl implements GenericDao<Persona> {

    @Override
    public void guardar(Persona persona) throws SQLException {

        String query = "INSERT INTO persona (nombre, apellido, dni, fr_key_domicilio) VALUES (?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, Integer.parseInt(persona.getDni()));
            stmt.setLong(4, persona.getDomicilio().getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Fallo al momento de insertar la persona. Las filas no se ven afectadas.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    persona.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para persona...");
                }
            }

            System.out.println("Persona guardada");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        if (id<=0){
            throw new IllegalArgumentException("El ID de la persona debe ser mayor que cero.");
        }

        String query = "DELETE FROM persona WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo eliminar la persona. No se encontró la persona con el ID " + id);
            }

            System.out.println("Persona eliminada");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Persona persona) throws SQLException {
        if(persona.getId()<=0){
            throw new IllegalArgumentException("El ID de la persona debe ser mayor que cero.");
        }

        String query = "UPDATE persona SET nombre = ?, apellido = ?, dni = ?, fr_key_domicilio = ? WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, Integer.parseInt(persona.getDni()));
            stmt.setLong(4, persona.getDomicilio().getId());
            stmt.setLong(5, persona.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar la persona. No se encontró la persona con el ID " + persona.getId());
            }

            System.out.println("Persona actualizada");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Persona buscarPorId(Long id) throws SQLException {
        if (id<=0){
            throw new IllegalArgumentException("El ID de la persona debe ser mayor que cero.");
        }
        String query = "SELECT p.*, d.calle, d.numero, d.localidad, d.provincia FROM persona p JOIN domicilio d ON p.fr_key_domicilio = d.id WHERE persona.id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        rs.getLong("d.id"),
                        rs.getString("d.calle"),
                        rs.getInt("d.numero"),
                        rs.getString("d.localidad"),
                        rs.getString("d.provincia")

                );
                return new Persona(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        domicilio
                );
            }else {
                throw new SQLException("No se encontró la persona con el ID " + id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Persona> buscarTodos() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String query = "SELECT p.*, d.calle, d.numero, d.localidad, d.provincia FROM persona p JOIN domicilio d ON p.fr_key_domicilio = d.id";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        new Domicilio(
                                rs.getLong("d.id"),
                                rs.getString("d.calle"),
                                rs.getInt("d.numero"),
                                rs.getString("d.localidad"),
                                rs.getString("d.provincia")
                        )
                );
                personas.add(persona);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }
}





