package ar.edu.utn.frba.dds.models.builders.fusionesDeComunidades;

import ar.edu.utn.frba.dds.models.domain.comunidades.Miembro;
import ar.edu.utn.frba.dds.models.domain.comunidades.PropuestaFusion;
import ar.edu.utn.frba.dds.models.domain.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.ComunidadFusionable;
import ar.edu.utn.frba.dds.models.domain.services_api.service_3.entities.PropuestaAnterior;
import ar.edu.utn.frba.dds.models.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.models.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.excepciones.puntajes.SinIdValidoExcepcion;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
