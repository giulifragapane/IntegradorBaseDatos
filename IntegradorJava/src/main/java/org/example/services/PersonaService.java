package org.example.services;

import org.example.models.Persona;

public class PersonaService implements GenericService<Persona>{

    @Override
    public void guardar(Persona entidad) {
        if(entidad.getDomicilio().getId()==0 || entidad==null){
            System.out.println("No se pudo guardar la persona.");

        }else if(entidad==null){
            System.out.println("Por favor cree una persona");
        }
    }

    @Override
    public void actualizar(Persona entidad) {

    }

    @Override
    public Persona filtrarId() {
        return null;
    }
}
