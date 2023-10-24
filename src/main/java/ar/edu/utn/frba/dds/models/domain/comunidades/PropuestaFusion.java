package ar.edu.utn.frba.dds.models.domain.comunidades;

import ar.edu.utn.frba.dds.models.builders.fusionesDeComunidades.PropuestaAnteriorBuilder;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.PropuestaAnterior;
import lombok.Setter;

import java.time.LocalDate;


@Setter
public class PropuestaFusion {

    private Comunidad comunidad;
    private LocalDate fechaSolicitada;

    public PropuestaAnterior propuestaAnterior(){
        return new PropuestaAnteriorBuilder().conId(this.comunidad.getId()).conFecha(this.fechaSolicitada).construir();
    }

}
