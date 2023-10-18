package ar.edu.utn.frba.dds.models.builders.puntajes;

import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.ComunidadPuntaje;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.entities.MiembroPuntaje;
import ar.edu.utn.frba.dds.models.excepciones.puntajes.SinIdValidoExcepcion;
import ar.edu.utn.frba.dds.models.excepciones.puntajes.SinPuntajeExcepcion;

public class ComunidadPuntajeBuilder {
    private ComunidadPuntaje comunidadPuntaje = new ComunidadPuntaje();

    public ComunidadPuntajeBuilder conId(Long id){
        this.comunidadPuntaje.id = Math.toIntExact(id);
        return this;
    }

    public ComunidadPuntajeBuilder conPuntaje(double puntaje){
        this.comunidadPuntaje.puntaje = puntaje;
        return this;
    }

    public ComunidadPuntaje construir(){
        if(this.comunidadPuntaje.id < 0){
            throw new SinIdValidoExcepcion();
        }

        return this.comunidadPuntaje;
    }
}
