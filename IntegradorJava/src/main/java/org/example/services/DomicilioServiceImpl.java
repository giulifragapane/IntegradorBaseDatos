package org.example.services;

import org.example.dao.DomicilioDaoImpl;
import org.example.dao.GenericDao;
import org.example.models.Domicilio;

import java.sql.SQLException;
import java.util.List;

public class DomicilioServiceImpl implements GenericService<Domicilio> {
    private final GenericDao<Domicilio> domicilioDao;

    public DomicilioServiceImpl(GenericDao<Domicilio> domicilioDao) {
        this.domicilioDao = domicilioDao;
    }

    @Override
    public void guardar(Domicilio domicilio) throws SQLException {
        validarDom(domicilio);
        domicilioDao.guardar(domicilio);
        System.out.println("Domicilio guardado con ID: " + domicilio.getId());
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        Domicilio domicilio = domicilioDao.buscarPorId(id);
        if (domicilioDao.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Domicilio con ID: " + id + " no encontrado.");
        }else {
            domicilioDao.eliminar(id);
            System.out.println("Domicilio con ID: " + id + " eliminado.");
        }
    }

    @Override
    public void actualizar(Domicilio domicilio) throws SQLException {
        validarDom(domicilio);
        domicilioDao.actualizar(domicilio);
        System.out.println("Domicilio con ID: "+domicilio.getId()+" actualizado.");

    }

    @Override
    public Domicilio buscarPorId(Long id) throws SQLException {
        Domicilio domicilio = domicilioDao.buscarPorId(id);
        if (domicilio == null) {
            throw new IllegalArgumentException("Domicilio con ID: " + id + " no encontrado.");
        }else {
            return domicilio;
        }
    }

    @Override
    public List<Domicilio> buscarTodos() throws SQLException {
        List<Domicilio> domicilios = domicilioDao.buscarTodos();
        if (!domicilios.isEmpty()) {
            System.out.println("Domicilios encontrados:"+domicilios.size());
            return domicilios;
        }else {
            throw new IllegalArgumentException("No se encontraron domicilios.");
        }

    }

    private void validarDom(Domicilio domicilio){
        if (domicilio == null) {
            throw new IllegalArgumentException("El domicilio no puede ser nulo.");
        }
        if (domicilio.getCalle() == null || domicilio.getCalle().isBlank()) {
            throw new IllegalArgumentException("El campo 'calle' no puede estar vacío.");
        }
        if (domicilio.getNumero() <= 0) {
            throw new IllegalArgumentException("El campo 'numero' debe ser mayor que cero.");
        }
        if (domicilio.getLocalidad() == null || domicilio.getLocalidad().isBlank()) {
            throw new IllegalArgumentException("El campo 'localidad' no puede estar vacío.");
        }
        if (domicilio.getProvincia() == null || domicilio.getProvincia().isBlank()) {
            throw new IllegalArgumentException("El campo 'provincia' no puede estar vacío.");
        }

        //agregamos limites para los caracteres

        if (domicilio.getCalle().length() > 50) {
            throw new IllegalArgumentException("El campo 'calle' no puede tener más de 50 caracteres.");
        }
        if (domicilio.getLocalidad().length() > 50) {
            throw new IllegalArgumentException("El campo 'localidad' no puede tener más de 50 caracteres.");
        }
        if (domicilio.getProvincia().length() > 50) {
            throw new IllegalArgumentException("El campo 'provincia' no puede tener más de 50 caracteres.");
        }
    }

}
