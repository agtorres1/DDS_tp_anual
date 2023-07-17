package ar.edu.utn.frba.dds.domain.serviciospublicos;

import ar.edu.utn.frba.dds.builders.EstablecimientoBuilder;
import ar.edu.utn.frba.dds.domain.localizaciones.Localizacion;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Establecimiento {
  String nombre;
  Ubicacion centroide;
  Set<PrestacionDeServicio> prestacionesDeServicios;
  TipoEstablecimiento tipoEstablecimiento;
  Localizacion localizacion;


  public void agregarPrestaciones(PrestacionDeServicio... prestacionDeServicios) {
    Collections.addAll(this.prestacionesDeServicios, prestacionDeServicios);
  }


  public void darDeBajaPrestaciones(PrestacionDeServicio... prestacionesDeServicios) {
    for (PrestacionDeServicio value : prestacionesDeServicios) {
      this.prestacionesDeServicios.remove(value);
    }
  }

  public void cambiarPrestacionDeServicio(Boolean funciona, PrestacionDeServicio... prestacionesDeServicios) {
    for (PrestacionDeServicio value : prestacionesDeServicios) {
      value.setFunciona(funciona);
    }
  }

}
