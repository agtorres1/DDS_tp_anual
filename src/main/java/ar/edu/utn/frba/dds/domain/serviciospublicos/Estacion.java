package ar.edu.utn.frba.dds.domain.serviciospublicos;

import ar.edu.utn.frba.dds.domain.servicios.Servicio;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Estacion {
  String nombre;
  Ubicacion centroide;
  List<Servicio> servicios;

  public Boolean algunServicioNoPrestado() {
    for (Servicio servicio : this.servicios) {
      if (!servicio.getFunciona()) {
        return false;
      }
    }
    return true;
  }

  public void agregarServicios(Servicio ... servicio) {
    Collections.addAll(this.servicios,servicio);
  }

  /**
   * param servicio: Lista de servicios
   */
  public void darDeBajaServicios(Servicio ... servicio) {
    for (Servicio value : servicio) {
      this.servicios.remove(value);
    }
  }
}
