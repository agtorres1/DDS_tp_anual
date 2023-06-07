package ar.edu.utn.frba.dds.domain.serviciospublicos;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicios;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Setter @Getter
public class Establecimiento {
  String nombre;
  Ubicacion centroide;
  Set<PrestacionDeServicios> prestacionesDeServicios;
  TipoEstablecimiento tipoEstablecimiento;


  public Establecimiento(TipoEstablecimiento tipoEstablecimiento) {
    this.tipoEstablecimiento = tipoEstablecimiento;
    this.prestacionesDeServicios = new HashSet<PrestacionDeServicios>();
  }

  public void agregarPrestaciones(PrestacionDeServicios ... prestacionDeServicios) {
    Collections.addAll(this.prestacionesDeServicios,prestacionDeServicios);
  }


  public void darDeBajaPrestaciones(PrestacionDeServicios ... prestacionesDeServicios) {
    for (PrestacionDeServicios value : prestacionesDeServicios) {
      this.prestacionesDeServicios.remove(value);
    }
  }

  public void cambiarPrestacionDeServicio(Boolean funciona, PrestacionDeServicios ... prestacionesDeServicios) {
    for (PrestacionDeServicios value : prestacionesDeServicios) {
      value.getServicio().setFunciona(funciona);
    }
  }

  public PrestacionDeServicios tengoEseServicio(Servicio servicio){
    for(PrestacionDeServicios prestacion : this.getPrestacionesDeServicios()){
      if(prestacion.getServicio() == servicio){
        return prestacion;
      }
    }
    return null;
  }
}
