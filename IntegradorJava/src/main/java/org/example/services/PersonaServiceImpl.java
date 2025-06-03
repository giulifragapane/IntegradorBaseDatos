package org.example.services;

import org.example.dao.PersonaDaoImpl;
import org.example.models.Persona;

import java.util.List;

public class PersonaServiceImpl implements GenericService<Persona>{
    private DomicilioServiceImpl domicilioService;
    private final PersonaDaoImpl personaDao = new PersonaDaoImpl();

    @Override
    public void guardar(Persona entity) throws Exception {

        if(entity.getDomicilio().getId()==null) {
            if (entity.getDomicilio()==null){
                System.out.println("La persona no tiene ningun domicilio, agregue uno");
                return;
            }else {
                System.out.println("No hay ningun domicilio guardado, guardar domicilio primero");
                return;
            }
        }
        personaDao.guardar(entity);

    }

    @Override
    public void eliminar(Persona entity) throws Exception {

    }

    @Override
    public void actualizar(Persona entity) throws Exception {

    }

    @Override
    public Persona buscar(Persona entity) throws Exception {
        return null;
    }

    @Override
    public List<Persona> buscarTodos() throws Exception {
        return List.of();
    }
}
