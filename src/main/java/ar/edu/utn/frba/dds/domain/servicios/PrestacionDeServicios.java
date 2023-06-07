package ar.edu.utn.frba.dds.domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PrestacionDeServicios {
    Servicio servicio;
    Integer cantidad;

    public PrestacionDeServicios(Servicio servicio, Integer cantidad){
        this.servicio = servicio;
        this.cantidad = cantidad;
    }
}

