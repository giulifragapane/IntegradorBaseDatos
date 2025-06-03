package org.example.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;
}
