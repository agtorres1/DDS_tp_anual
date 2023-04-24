package ar.edu.utn.frba.dds.domain.servicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Servicio {
  private Boolean funciona;
  private String nombre;

  public void cambiarPrestacion(Boolean funciona) {
    this.funciona = funciona;
  }
}
