package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Persona {
    private int id;
    private String nombre, apellido, email, telefono;
    private Date fechaNacimiento;
    private Domicilio domicilio;


}
