package ar.edu.utn.frba.dds.domain.comunidades;

import ar.edu.utn.frba.dds.domain.incidentes.Incidente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frba.dds.domain.servicios.PrestacionDeServicio;
import ar.edu.utn.frba.dds.domain.serviciospublicos.Establecimiento;
import ar.edu.utn.frba.dds.excepciones.NoEsUnaPrestacionValidaExcepcion;
import ar.edu.utn.frba.dds.excepciones.PrestacionFuncionaExcepcion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comunidad{
  private List<Miembro> administradores;
  private List<Miembro> miembros;
  private List<Incidente> incidentes;
  private String nombre;

  public Comunidad(String nombre) {
    this.nombre = nombre;
    this.administradores = new ArrayList<>();
    this.miembros = new ArrayList<>();
  }

  public Boolean actualizarPrestacionDeServicio(Establecimiento establecimiento, PrestacionDeServicio prestacionDeServicio){
    if(!establecimiento.getPrestacionesDeServicios().contains(prestacionDeServicio)){
      throw new NoEsUnaPrestacionValidaExcepcion();
    }
    return prestacionDeServicio.getFunciona();
  }
/*



  public void ingresarServicioNuevo(Establecimiento establecimiento, Miembro administrador, String nombre, String descripcion, int cantidad) throws NoEsAdministradorExcepcion {
    this.verificarQueEsAdministrador(administrador);
    ServicioComunitario servicio = new ServicioComunitario();
    servicio.setNombre(nombre);
    servicio.setDescripcion(descripcion);
    PrestacionDeServicio prestacionDeServicio = new PrestacionDeServicio(servicio,cantidad);
    prestacionDeServicio.setFunciona(false);
    establecimiento.agregarPrestaciones(prestacionDeServicio);
  }

  public void verificarQueEsAdministrador(Miembro administrador) throws NoEsAdministradorExcepcion{
    if (!this.administradores.contains(administrador)) {
      throw new NoEsAdministradorExcepcion();
    }
  }
*/
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

  public void abrirIncidente(Miembro autor, String observaciones,Establecimiento establecimiento ,PrestacionDeServicio prestacionDeServicio){
    if(actualizarPrestacionDeServicio(establecimiento,prestacionDeServicio)){
      throw new PrestacionFuncionaExcepcion();
    }
    Incidente incidente = new Incidente();
    incidente.meAbro(autor,observaciones,establecimiento,prestacionDeServicio);
    for (Miembro miembro : this.miembros) {
      //notificamos a cada miembro del incidente
    }


  }
  public void cerrarIncidente(Miembro autor,Incidente incidente){
    incidente.meCierro(autor);
    for(Miembro miembro : this.miembros){
      //notificamos a cada miembro que se cerró el incidente
    }
  }

}