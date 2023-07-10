package ar.edu.utn.frba.dds.domain.serviciospublicos;

import lombok.Getter;
import lombok.Setter;

import static ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEntidad.LINEA_TRANSPORTE;

@Setter
@Getter
public class Serviciopublico {
  ServicioPublicoPosible servicioPublicoPosible;
  Entidad entidad;
  public Serviciopublico(Entidad entidad, ServicioPublicoPosible servicioPublicoPosible){
    if(!entidad.getTipoEntidad().generarListadoDeCategorias().contains(servicioPublicoPosible)){
      throw new IllegalArgumentException("No es una categoria valida para el tipo de entidad generado");
    }
    if(entidad.getTipoEntidad()==LINEA_TRANSPORTE && (!(entidad.getEstablecimientos().size() >= 2))){
        throw new IllegalArgumentException("La lista de estaciones debe incluir un origen y un destino.");
    }
    this.servicioPublicoPosible = servicioPublicoPosible;
    this.entidad = entidad;
  }
}
