package ar.edu.utn.frba.dds.domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Servicio {
  private Boolean funciona;

  public void cambiarPrestacion(Boolean funciona) {
    this.funciona = funciona;
  }


}
