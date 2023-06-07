package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicios;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.domain.servicios.ServicioComunitario;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comunidad{
  private List<Miembro> administradores;
  private List<Miembro> miembros;
  private String nombre;

  public Comunidad(String nombre) {
    this.nombre = nombre;
    this.administradores = new ArrayList<>();
    this.miembros = new ArrayList<>();
  }

  public void ingresarServicioExistente(Establecimiento establecimiento, Miembro administrador, PrestacionDeServicios prestacionDeServicios) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    establecimiento.agregarPrestaciones(prestacionDeServicios);
  }

  public void quitarServicio(Establecimiento establecimiento, Miembro administrador, PrestacionDeServicios prestacionDeServicios) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    establecimiento.darDeBajaPrestaciones(prestacionDeServicios);
  }

  public void cambiarPrestacionDeServicio(Establecimiento establecimiento, Miembro administrador, PrestacionDeServicios prestacionDeServicios, Boolean funciona) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    establecimiento.cambiarPrestacionDeServicio(funciona, prestacionDeServicios);
  }

  public void ingresarServicioNuevo(Establecimiento establecimiento, Miembro administrador, String nombre, String descripcion, int cantidad) throws NoEsAdministradorExcepcion {
    this.verificarQueEsAdministrador(administrador);
    ServicioComunitario servicio = new ServicioComunitario();
    servicio.setNombre(nombre);
    servicio.setDescripcion(descripcion);
    servicio.setFunciona(false);
    PrestacionDeServicios prestacionDeServicios = new PrestacionDeServicios(servicio,cantidad);
    establecimiento.agregarPrestaciones(prestacionDeServicios);
  }

  public void verificarQueEsAdministrador(Miembro administrador) throws NoEsAdministradorExcepcion{
    if (!this.administradores.contains(administrador)) {
      throw new NoEsAdministradorExcepcion();
    }
  }

  public void agregarUsuarios(Miembro... miembros) {
    this.miembros.addAll(Arrays.asList(miembros));
  }

  public void agregarAdministradores(Miembro... administradores) {
    this.administradores.addAll(Arrays.asList(administradores));
  }

  public void removerUsuarios(Miembro... miembros) {
    for (Miembro value : miembros) {
      this.miembros.remove(value);
    }
  }
  public void removerAdministradores(Miembro... administradores) {
      for (Miembro value : administradores){
        this.administradores.remove(value);
      }
    }
}