package org.example.dao;

public interface GenericDao<T> {
    void guardad(T entidad);
    void actualizar(T entidad);
    T filtrarId();
}
