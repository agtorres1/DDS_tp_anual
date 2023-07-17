package ar.edu.utn.frba.dds.domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Escalador implements Servicio {
  private TipoTraslado origen;
  private TipoTraslado destino;
  private TipoEscalador tipoEscalador;
}
