package ar.edu.utn.frba.dds.domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServicioComunitario implements Servicio{
    private String nombre;
    private String descripcion;
}
