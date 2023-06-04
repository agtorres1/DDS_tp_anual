package ar.edu.utn.frba.dds.domain.serviciospublicos;

import lombok.Getter;
import lombok.Setter;

import static ar.edu.utn.frba.dds.domain.serviciospublicos.TipoEntidad.LINEA_TRASPORTE;

@Setter
@Getter
public class Serviciopublico {
  Entidad entidad;

  Serviciopublico(Entidad entidad){
    if(entidad.getTipoEntidad()==LINEA_TRASPORTE && (!(entidad.getEstablecimientos().size() >1))){
        throw new IllegalArgumentException("La lista de estaciones debe incluir el origen y el destino.");
    }
  }
}
