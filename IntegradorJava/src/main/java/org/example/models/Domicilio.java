package org.example.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Domicilio {
    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;
}
