package org.example.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;


    public Persona(String nombre, String apellido, String dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
