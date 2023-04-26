package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.excepciones.NoEsAdministradorExcepcion;
import ar.edu.utn.frba.dds.domain.servicios.Servicio;
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
  public void ingresarServicio(Estacion estacion, Usuario administrador, Servicio servicio) throws NoEsAdministradorExcepcion{
    if (!this.administradores.contains(administrador)) {
      throw new NoEsAdministradorExcepcion();
    }
    estacion.agregarServicios(servicio);
  }

  public void quitarServicio(Estacion estacion, Usuario administrador, Servicio servicio) throws NoEsAdministradorExcepcion{
    if (!this.administradores.contains(administrador)) {
      throw new NoEsAdministradorExcepcion();
    }
    estacion.darDeBajaServicios(servicio);
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
}