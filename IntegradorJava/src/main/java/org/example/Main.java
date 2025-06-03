package org.example;


import lombok.Builder;
import org.example.dao.DomicilioDaoImpl;
import org.example.dao.GenericDao;
import org.example.dao.PersonaDaoImpl;
import org.example.models.Domicilio;
import org.example.models.Persona;
import org.example.services.DomicilioServiceImpl;
import org.example.services.GenericService;
import org.example.services.PersonaServiceImpl;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@Builder
public class Main {
    public static void main(String[] args) {

        System.out.println("Iniciando...");

        GenericDao<Domicilio> domicilioDao = new DomicilioDaoImpl();
        GenericDao<Persona> personaDao = new PersonaDaoImpl();


        GenericService<Domicilio> domicilioService = new DomicilioServiceImpl(domicilioDao);
        GenericService<Persona> personaService = new PersonaServiceImpl(personaDao, domicilioService);

        try {
            //instanciamos un domicilio
            System.out.println("Creando domicilio...");
            Domicilio dom1 = Domicilio.builder()
                    .calle("San Martin")
                    .numero(2301)
                    .localidad("CABA")
                    .provincia("Buenos Aires")
                            .build();
            domicilioService.guardar(dom1);
            //instanciamos una persona
            System.out.println(" Creando persona...");
            Persona p1 =  Persona.builder()
                    .nombre("Juan")
                    .apellido("López")
                    .dni("44956224")
                    .domicilio(dom1)
                    .build();
            personaService.guardar(p1);

            //instanciamos un domicilio
            System.out.println("Creando domicilio...");
            Domicilio dom2 = Domicilio.builder()
                    .calle("Belgrano")
                    .numero(7001)
                    .localidad("Ciudad")
                    .provincia("Mendoza")
                    .build();
            domicilioService.guardar(dom2);

            //instanciamos una persona
            System.out.println(" Creando persona...");
            Persona p2 =  Persona.builder()
                    .nombre("Maria")
                    .apellido("González")
                    .dni("36554714")
                    .domicilio(dom2)
                    .build();
            personaService.guardar(p2);

            //listando las personas instanciadas
            System.out.println("Listando a las personas...");
            List<Persona> personas = personaService.buscarTodos();
            for (Persona p : personas) {
                System.out.println(" * " + p);
            }
            System.out.println("\n------------------------------------------------------------");

            //Buscamos a una persona mediante el ID
            System.out.println("Buscamos una persona con id 2:");
            System.out.println(personaService.buscarPorId(2L));

            //Eliminamos a una persona por ID
            System.out.println("Eliminando la persona seleccionada...");
           personaService.eliminar(4L);

            //Actualizamos una persona
            System.out.println("Actualizamos a la persona con id 2, cambiando su apellido.");
            p2.setApellido("Martinez");
            personaService.actualizar(p2);

            System.out.println("\n------------------------------------------------------------");

            //Volvemos a listar personas
            System.out.println(" Listado de personas actualizado...");
            List<Persona> personasActualizado = personaService.buscarTodos();
            for (Persona p : personasActualizado) {
                System.out.println(" * " + p);
            }
        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
