package org.example.services;

import java.util.List;

public interface GenericService <T>{
    void guardar(T entity)throws Exception;
    void eliminar(T entity)throws Exception;
    void actualizar(T entity)throws Exception;
    T buscar(T entity)throws Exception;
    List<T> buscarTodos()throws Exception;
}
