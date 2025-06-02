package org.example.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
//import org.example.services.DomicilioService;
import org.example.services.DomicilioServiceImpl;

@Getter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Domicilio {


    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

    private DomicilioServiceImpl domicilioService = new DomicilioServiceImpl();

    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void guardar(Domicilio domicilio) throws Exception{
        domicilioService.guardar(domicilio);
    }


}
