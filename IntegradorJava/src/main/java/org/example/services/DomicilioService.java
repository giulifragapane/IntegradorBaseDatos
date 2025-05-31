package org.example.services;

import org.example.dao.DomicilioDaoImpl;
import org.example.models.Domicilio;

public class DomicilioService implements GenericService<Domicilio>{


    @Override
    public void guardar(Domicilio entidad) {
        if (entidad.getId() == 0){
            System.out.println("No se ha creado ningun domicilio");
        }else {
            DomicilioDaoImpl.guardar(entidad);
        }

    }

    @Override
    public void actualizar(Domicilio entidad) {

    }

    @Override
    public Domicilio filtrarId() {
        return null;
    }
}
