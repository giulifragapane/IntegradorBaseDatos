package org.example.services;

public interface GenericService<T> {
    void guardar(T entidad);
    void actualizar(T entidad);
     T filtrarId();
}
