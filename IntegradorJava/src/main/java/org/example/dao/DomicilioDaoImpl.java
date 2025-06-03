package org.example.dao;
import org.example.models.Domicilio;
import org.example.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DomicilioDaoImpl implements GenericDao<Domicilio> {

    @Override
    public void guardar(Domicilio domicilio) throws SQLException {
        String query = "INSERT INTO domicilio (calle, numero, localidad, provincia) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, domicilio.getCalle());
            stmt.setInt(2, domicilio.getNumero());
            stmt.setString(3, domicilio.getLocalidad());
            stmt.setString(4, domicilio.getProvincia());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Fallo al momento de insertar el domicilio. Las filas no se ven afectadas.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    domicilio.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para domicilio...");
                }
            }

            System.out.println("Domicilio guardado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del domicilio debe ser mayor que cero.");
        }
        String query = "DELETE FROM domicilio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo eliminar el domicilio. No se encontró el domicilio con el ID " + id);
            }

            System.out.println("Domicilio eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Domicilio domicilio) throws SQLException {
        if(domicilio.getId()<=0){
            throw new IllegalArgumentException("El ID del domicilio debe ser mayor que cero.");
        }
        String query = "UPDATE domicilio SET calle = ?, numero = ?, localidad = ?, provincia = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, domicilio.getCalle());
            stmt.setInt(2, domicilio.getNumero());
            stmt.setString(3, domicilio.getLocalidad());
            stmt.setString(4, domicilio.getProvincia());
            stmt.setLong(5, domicilio.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el domicilio. No se encontró el domicilio con el ID " + domicilio.getId());
            }

            System.out.println("Domicilio actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Domicilio buscarPorId(Long id) throws SQLException {
        if (id<=0){
            throw new IllegalArgumentException("El ID del domicilio debe ser mayor que cero.");
        }
        String query = "SELECT * FROM domicilio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Domicilio(
                        rs.getLong("id"),
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("localidad"),
                        rs.getString("provincia")
                );
            }else {
                throw new SQLException("No se encontró el domicilio con el ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() throws SQLException {
        List<Domicilio> domicilios = new ArrayList<>();
        String query = "SELECT * FROM domicilio ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        rs.getLong("id"),
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("localidad"),
                        rs.getString("provincia")
                );
                domicilios.add(domicilio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return domicilios;
    }
}

