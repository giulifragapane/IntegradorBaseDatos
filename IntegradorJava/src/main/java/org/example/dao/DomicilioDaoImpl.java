package org.example.dao;
import org.example.models.Domicilio;
import org.example.config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DomicilioDaoImpl implements GenericDao<Domicilio> {

    @Override
    public void guardar(Domicilio entity) throws SQLException {
        String query = "INSERT INTO domicilio (calle, numero, localidad, provincia) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setString(1, entity.getCalle());
            stmn.setInt(2, entity.getNumero());
            stmn.setString(3, entity.getLocalidad());
            stmn.setString(4, entity.getProvincia());
            stmn.executeUpdate();
            System.out.println("Domicilio guardado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

        String query = "DELETE FROM domicilio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setLong(1, id);
            stmn.executeUpdate();
            System.out.println("Domicilio eliminado");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Domicilio entity) throws SQLException {
        String query = "UPDATE domicilio SET calle = ?, numero = ?, localidad = ?, provincia = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setString(1, entity.getCalle());
            stmn.setInt(2, entity.getNumero());
            stmn.setString(3, entity.getLocalidad());
            stmn.setString(4, entity.getProvincia());
            stmn.setLong(5, entity.getId());
            stmn.executeUpdate();
            System.out.println("Domicilio actualizado");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Domicilio buscarPorId(Long id) throws SQLException {

        String query = "SELECT * FROM domicilio WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmn = conn.prepareStatement(query)) {
            stmn.setLong(1, id);
            ResultSet rs = stmn.executeQuery();
            if (rs.next()) {
                return new Domicilio(
                        //rs.getLong("id"),
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("localidad"),
                        rs.getString("provincia")
                );

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() throws SQLException {
        List<Domicilio> domicilios = new ArrayList<>();
        String query = "SELECT * FROM domicilio";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmn = conn.prepareStatement(query);
             ResultSet rs = stmn.executeQuery(query)){
            while (rs.next()) {
                Domicilio domicilio = new Domicilio(
                        //rs.getLong("id"),
                        rs.getString("calle"),
                        rs.getInt("numero"),
                        rs.getString("localidad"),
                        rs.getString("provincia")
                );
                domicilios.add(domicilio);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return domicilios;
    }
}

