package org.example.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Domicilio domicilio;
}
