package ar.edu.utn.frba.dds.domain.serviciospublicos;

import ar.edu.utn.frba.dds.domain.servicios.Servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Establecimiento {
  String nombre;
  Ubicacion centroide;
  List<Servicio> servicios;


  public Establecimiento() {
    this.servicios = new ArrayList<>();
  }

  /*
    Ejemplo posible de funcion a utilizar en el futuro
  */
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

  public void cambiarPrestacionDeServicios(Boolean funciona, Servicio ... servicio) {
    for (Servicio value : servicio) {
      value.setFunciona(funciona);
    }
  }
}
