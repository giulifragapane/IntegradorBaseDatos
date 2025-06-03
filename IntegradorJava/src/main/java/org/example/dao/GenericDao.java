package org.example.dao;

import org.example.models.Persona;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> {



    void guardar(T entity) throws SQLException;
    void eliminar(Long id)throws SQLException;
    void actualizar(T entity)throws SQLException;
    T buscarPorId(Long id)throws SQLException;
    List<T> buscarTodos()throws SQLException;

}
