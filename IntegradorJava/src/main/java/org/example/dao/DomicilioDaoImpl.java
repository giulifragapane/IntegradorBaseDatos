package org.example.dao;

import org.example.models.Domicilio;
import org.example.models.Persona;

import java.sql.SQLException;
import java.util.List;

public class DomicilioDaoImpl implements GenericDao<Domicilio> {

    public void save(Domicilio entity){
        String query = "INSERT INTO domicilio (calle, numero, localidad, provincia) VALUES (?, ?, ?, ?)";


    }

    @Override
    public void guardar(Domicilio entity) throws SQLException {

    }



    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public void actualizar(Domicilio entity) throws SQLException {

    }

    @Override
    public Domicilio buscar(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() throws SQLException {
        return List.of();
    }
}
