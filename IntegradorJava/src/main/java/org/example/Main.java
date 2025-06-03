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
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@Builder
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Iniciando...");

        GenericDao<Domicilio> domicilioDao = new DomicilioDaoImpl();
        GenericDao<Persona> personaDao = new PersonaDaoImpl();


        GenericService<Domicilio> domicilioService = new DomicilioServiceImpl(domicilioDao);
        GenericService<Persona> personaService = new PersonaServiceImpl(personaDao, domicilioService);

        try {
            int opcion;
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
            //condicional para decidir si continuar en la ejecucion
            System.out.println("A continuacion se crearán otra persona y domicilio...\nSi deseas continuar presiona 1");
            opcion=sc.nextInt();
            sc.nextLine();
            if (opcion!=1){
                System.out.println("Saliendo...");
                System.exit(0);
            }

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
            System.out.println("\n------------------------------------------------------------");
            System.out.println("A continuacion se listarán las personas guardadas...\nSi deseas continuar presiona 1");
            opcion=sc.nextInt();
            sc.nextLine();
            if (opcion!=1){
                System.out.println("Saliendo...");
                System.exit(0);
            }

            //listando las personas instanciadas
            System.out.println("Listando a las personas...");
            List<Persona> personas = personaService.buscarTodos();
            for (Persona p : personas) {
                System.out.println(" * " + p);
            }
            System.out.println("\n------------------------------------------------------------");
            System.out.println("A continuacion se buscará una persona con un id especifico...\nSi deseas continuar presiona 1");
            opcion=sc.nextInt();
            sc.nextLine();
            if (opcion!=1){
                System.out.println("Saliendo...");
                System.exit(0);
            }
            //Buscamos a una persona mediante el ID
            System.out.println("Ingrese el numero de id a buscar...");
            Long id = sc.nextLong();
            System.out.println("Buscamos una persona con id "+id +":");
            System.out.println(personaService.buscarPorId(id));
            System.out.println("\n------------------------------------------------------------");
            System.out.println("A continuacion se eliminará una persona por su id...\nSi deseas continuar presiona 1");
            opcion=sc.nextInt();
            sc.nextLine();
            if (opcion!=1){
                System.out.println("Saliendo...");
                System.exit(0);
            }
            //Eliminamos a una persona por ID
            System.out.println("Ingrese el numero de id a eliminar...");
            id = sc.nextLong();
            System.out.println("Eliminando la persona seleccionada...");
            personaService.eliminar(id);
            System.out.println("\n------------------------------------------------------------");
            //Actualizamos una persona
            System.out.println("A continuacion se actualizará una persona...\nSi deseas continuar presiona 1");
            opcion=sc.nextInt();
            sc.nextLine();
            if (opcion!=1){
                System.out.println("Saliendo...");
                System.exit(0);
            }
            System.out.println("Ingrese el numero de id a actualizar...");
            id = sc.nextLong();
            System.out.println("Actualizamos a la persona con id "+id+", cambiando su apellido.");
            Persona persona= personaService.buscarPorId(id);
            persona.setApellido("Gonzalez");
            personaService.actualizar(persona);

            System.out.println("\n------------------------------------------------------------");

            //Volvemos a listar personas
            System.out.println("A continuacion se volverán a listar las personas tras los cambios generados...\nSi deseas continuar presiona 1");
            opcion=sc.nextInt();
            sc.nextLine();
            if (opcion!=1){
                System.out.println("Saliendo...");
                System.exit(0);
            }
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
