package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
import ar.edu.utn.frba.dds.domain.servicios.ServicioComunitario;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Estacion;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comunidad{
  private List<Usuario> administradores;
  private List<Usuario> miembros;
  private String nombre;

  /**
   * @param estacion:      Estacion a modificar
   * @param administrador: Administrador que realiza la modifiación
   * @param servicio:      Servicio a agregar
   */
  public void ingresarServicioExistente(Estacion estacion, Usuario administrador, Servicio servicio) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    estacion.agregarServicios(servicio);
  }

  public void quitarServicio(Estacion estacion, Usuario administrador, Servicio servicio) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    estacion.darDeBajaServicios(servicio);
  }

  public void cambiarPrestacionDeServicio(Estacion estacion, Usuario administrador, Servicio servicio, Boolean funciona) throws NoEsAdministradorExcepcion{
    this.verificarQueEsAdministrador(administrador);
    estacion.cambiarPrestacionDeServicios(funciona, servicio);
  }

  public void ingresarServicioNuevo(Estacion estacion, Usuario administrador, String nombre, String descripcion) throws NoEsAdministradorExcepcion {
    this.verificarQueEsAdministrador(administrador);
    ServicioComunitario servicio = new ServicioComunitario();
    servicio.setNombre(nombre);
    servicio.setDescripcion(descripcion);
    servicio.setFunciona(false);
    estacion.agregarServicios(servicio);
  }

  private void verificarQueEsAdministrador(Usuario administrador) throws NoEsAdministradorExcepcion{
    if (!this.administradores.contains(administrador)) {
      throw new NoEsAdministradorExcepcion();
    }
  }

  public void agregarUsuarios(Usuario... usuarios) {
    this.miembros.addAll(Arrays.asList(usuarios));
  }

  public void agregarAdministradores(Usuario... administradores) {
    this.administradores.addAll(Arrays.asList(administradores));
  }

  public void removerUsuarios(Usuario... usuarios) {
    for (Usuario value : usuarios) {
      this.miembros.remove(value);
    }
  }
  public void removerAdministradores(Usuario... administradores) {
      for (Usuario value : administradores){
        this.administradores.remove(value);
      }
    }
}