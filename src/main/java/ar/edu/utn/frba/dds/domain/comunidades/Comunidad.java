package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.domain.servicios.ServicioComunitario;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Estacion;

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

  /**
   * @param estacion:      Estacion a modificar
   * @param administrador: Administrador que realiza la modifiación
   * @param servicio:      Servicio a agregar
   */
  public void ingresarServicioExistente(Estacion estacion, Miembro administrador, Servicio servicio) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    estacion.agregarServicios(servicio);
  }

  public void quitarServicio(Estacion estacion, Miembro administrador, Servicio servicio) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    estacion.darDeBajaServicios(servicio);
  }

  public void cambiarPrestacionDeServicio(Estacion estacion, Miembro administrador, Servicio servicio, Boolean funciona) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    estacion.cambiarPrestacionDeServicios(funciona, servicio);
  }

  public void ingresarServicioNuevo(Estacion estacion, Miembro administrador, String nombre, String descripcion) throws NoEsAdministradorExcepcion {
    this.verificarQueEsAdministrador(administrador);
    ServicioComunitario servicio = new ServicioComunitario();
    servicio.setNombre(nombre);
    servicio.setDescripcion(descripcion);
    servicio.setFunciona(false);
    estacion.agregarServicios(servicio);
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