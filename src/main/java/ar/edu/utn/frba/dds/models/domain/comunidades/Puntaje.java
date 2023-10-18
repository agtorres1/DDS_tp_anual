package ar.edu.utn.frba.dds.models.domain.comunidades;

import ar.edu.utn.frba.dds.models.domain.services_api.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.models.domain.services_api.service_2.ServicioCalculador;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Getter
@Setter
public class Puntaje {

    @Column("puntaje")
    private Double valor;

    @Column("grado_de_confianza")
    private GradoDeConfianza gradoDeConfianza;

    public void actualizarPuntaje(double puntajeNuevo){
        this.valor = puntajeNuevo;

    }


}
