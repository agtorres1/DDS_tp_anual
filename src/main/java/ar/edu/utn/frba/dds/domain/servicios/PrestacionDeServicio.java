package ar.edu.utn.frba.dds.domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PrestacionDeServicio {
    private Servicio servicio;
    private Integer cantidad;
    private Boolean funciona;


    public PrestacionDeServicio(Servicio servicio, Integer cantidad){
        this.servicio = servicio;
        this.cantidad = cantidad;
    }
}

