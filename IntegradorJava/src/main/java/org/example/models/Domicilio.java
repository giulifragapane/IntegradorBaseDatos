package org.example.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Domicilio {
    private int  id, numero, cp;
    private String calle;


}
