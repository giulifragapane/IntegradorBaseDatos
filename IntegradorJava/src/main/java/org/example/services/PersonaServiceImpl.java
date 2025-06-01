package org.example.services;

import org.example.dao.GenericDao;
import org.example.dao.PersonaDaoImpl;
import org.example.models.Domicilio;
import org.example.models.Persona;

public class PersonaServiceImpl implements GenericService<Persona> {
    private final GenericDao<Persona> personaDao;
    private final GenericService<Domicilio> domicilioDao;
    private final DomicilioServiceImpl domicilioService;
    //private final PersonaDaoImpl personaDao;

    public PersonaServiceImpl(DomicilioServiceImpl domicilioService, PersonaDaoImpl personaDao) {
        this.domicilioService = domicilioService;
        this.personaDao = personaDao;
    }
    public void save(Persona entity){
        //validar
        if(entity.getDomicilio().getID==null){
            domicilioService.save(entity.getDomicilio());
        }

    }
}
