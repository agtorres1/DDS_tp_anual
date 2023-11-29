package ar.edu.utn.frba.dds.models.builders.fusionesDeComunidades;

import ar.edu.utn.frba.dds.models.domain.services_api.fusionadorComunidades.entities.PropuestaAnterior;
import ar.edu.utn.frba.dds.models.excepciones.puntajes.SinIdValidoExcepcion;

import java.time.LocalDate;

public class PropuestaAnteriorBuilder {
    private PropuestaAnterior propuestaAnterior = new PropuestaAnterior();

    public PropuestaAnteriorBuilder conId(Long id){
        this.propuestaAnterior.idComunidad = Math.toIntExact(id);
        return this;
    }

    public PropuestaAnteriorBuilder conFecha(LocalDate fecha){
        this.propuestaAnterior.fecha = fecha.toString();
        return this;
    }

    public PropuestaAnterior construir(){
        if(this.propuestaAnterior.idComunidad < 0){
            throw new SinIdValidoExcepcion();
        }

        return this.propuestaAnterior;
    }
}
