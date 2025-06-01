package org.example.services;

import org.example.dao.DomicilioDaoImpl;
import org.example.models.Domicilio;

import java.util.List;

public class DomicilioServiceImpl implements GenericService<Domicilio> {
    private final DomicilioDaoImpl domicilioDao;

    public DomicilioServiceImpl(DomicilioDaoImpl domicilioDao) {
        this.domicilioDao = domicilioDao;
    }
    public void save(Domicilio entity){
        //valido
        domicilioDao.save(entity);
    }

    @Override
    public void guardar(Domicilio entity) throws Exception {

    }

    @Override
    public void eliminar(Domicilio entity) throws Exception {

    }

    @Override
    public void actualizar(Domicilio entity) throws Exception {

    }

    @Override
    public Domicilio buscar(Domicilio entity) throws Exception {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() throws Exception {
        return List.of();
    }
}
