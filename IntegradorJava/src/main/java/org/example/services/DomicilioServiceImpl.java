package org.example.services;

import org.example.dao.DomicilioDaoImpl;
import org.example.models.Domicilio;

public class DomicilioServiceImpl implements GenericService<Domicilio> {
    private final DomicilioDaoImpl domicilioDao;

    public DomicilioServiceImpl(DomicilioDaoImpl domicilioDao) {
        this.domicilioDao = domicilioDao;
    }
    public void save(Domicilio entity){
        //valido
        domicilioDao.save(entity);
    }

}
