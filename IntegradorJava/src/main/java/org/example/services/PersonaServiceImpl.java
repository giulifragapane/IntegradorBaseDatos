package org.example.services;

import org.example.dao.GenericDao;
import org.example.dao.PersonaDaoImpl;
import org.example.models.Domicilio;
import org.example.models.Persona;

import java.sql.SQLException;
import java.util.List;

public class PersonaServiceImpl implements GenericService<Persona> {
    private final GenericDao<Persona> personaDao;
    private final GenericService<Domicilio> domicilioService;


    public PersonaServiceImpl(GenericDao<Persona> personaDao, GenericService<Domicilio> domicilioService) {
        this.personaDao = personaDao;
        this.domicilioService = domicilioService;
    }

    @Override
    public void guardar(Persona persona) throws SQLException {
        validarPersona(persona);
        if(persona.getDomicilio() != null && persona.getDomicilio().getId() <= 0) {
            domicilioService.guardar(persona.getDomicilio());
        }
        personaDao.guardar(persona);
        System.out.println("La persona '"+persona.getNombre()+"' fue guardada con éxito.");
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        Persona persona = personaDao.buscarPorId(id);
        if (personaDao.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Persona con ID: " + id + " no encontrado.");
        }else {
            personaDao.eliminar(id);
            System.out.println("Persona con ID: " + id + " eliminado.");
        }
    }

    @Override
    public void actualizar(Persona persona) throws SQLException {
        validarPersona(persona);
        personaDao.actualizar(persona);
        System.out.println("Persona con ID: "+persona.getId()+" actualizado.");
    }

    @Override
    public Persona buscarPorId(Long id) throws SQLException {
        Persona persona = personaDao.buscarPorId(id);
        if (persona == null) {
            throw new IllegalArgumentException("Persona con ID: " + id + " no encontrado.");
        }else {
            return persona;
        }
    }

    @Override
    public List<Persona> buscarTodos() throws SQLException {
        List<Persona> personas = personaDao.buscarTodos();
        if (personas.isEmpty() || personas == null) {
            throw new IllegalArgumentException("No se encontraron personas.");
        }else {
            System.out.println("Personas encontradas:"+personas.size());
            return personas;
        }
    }

    private void validarPersona(Persona persona) throws SQLException {
        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula.");
        }
        if (persona.getNombre() == null || persona.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la persona no puede ser nulo o estar vacío.");
        }
        if (persona.getApellido() == null || persona.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido de la persona no puede ser nulo o estar vacío.");
        }
        if (persona.getDni() == null || persona.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI de la persona no puede ser nulo o estar vacío.");
        }
        if (persona.getDomicilio() == null) {
            throw new IllegalArgumentException("El domicilio de la persona no puede ser nulo.");
        }

        //agregamos limites para los caracteres

        if (persona.getNombre().length() > 50) {
            throw new IllegalArgumentException("El nombre de la persona no puede tener más de 50 caracteres.");
        }
        if (persona.getApellido().length() > 50) {
            throw new IllegalArgumentException("El apellido de la persona no puede tener más de 50 caracteres.");
        }
        if (persona.getDni().length() > 10) {
            throw new IllegalArgumentException("El DNI de la persona no puede tener más de 10 caracteres.");
        }

    }
}
