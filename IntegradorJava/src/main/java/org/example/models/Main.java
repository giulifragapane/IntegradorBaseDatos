package org.example.models;



import lombok.NoArgsConstructor;
import org.example.config.DatabaseConnection;
import org.example.models.Domicilio;
import org.example.services.DomicilioServiceImpl;
import org.example.services.DomicilioServiceImpl;
import org.example.services.GenericService;
import org.example.services.PersonaServiceImpl;
import org.example.services.PersonaServiceImpl;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        GenericService<Persona> personaService  = new PersonaServiceImpl();
        GenericService<Domicilio> domicilioService = new DomicilioServiceImpl();
        Domicilio dom1 =  Domicilio.builder()
                .localidad("Godoy Cruz")
                .provincia("Mendoza")
                .numero(38)
                .calle("Cordoba")
                .build();

        Domicilio dom2 =  Domicilio.builder()
                .localidad("Lujan")
                .provincia("Mendoza")
                .numero(156)
                .calle("Belgrano")
                .build();

        Persona p1 = Persona.builder()
                .nombre("Luciano")
                .apellido("Molaro")
                .dni("42975965")
                .domicilio(dom1)
                .build();

        Persona p2 = Persona.builder()
                .nombre("Martin")
                .apellido("De Longo")
                .dni("42975965")
                .domicilio(dom2)
                .build();
        try {
            domicilioService.guardar(dom1);
            personaService.guardar(p1);
            personaService.guardar(p2);

        } catch (Exception e) {
            throw new RuntimeException("No se pudo guardar");
        }

    }




}